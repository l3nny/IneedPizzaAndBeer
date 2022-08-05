package com.example.ineedpizzabeer.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ineedpizzabeer.R
import com.example.ineedpizzabeer.data.repository.IRepository
import com.example.ineedpizzabeer.domain.model.Businesses
import com.example.ineedpizzabeer.domain.model.BusinessesResponse
import com.example.ineedpizzabeer.presentation.adapter.GenericRecyclerBindingAdapter
import com.example.ineedpizzabeer.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: IRepository,
    private val dispatcher: CoroutineContextProvider
) : ViewModel() {
    private var businessesList = arrayListOf<Businesses>()
    private var businessesMap: HashMap<String, Businesses> = HashMap()

    private var _loading = MutableStateFlow(false)
    var loading = _loading.asStateFlow()
    private var _triggerErrorDialog = MutableStateFlow("")
    var triggerErrorDialog = _triggerErrorDialog.asStateFlow()

    val businessesListAdapter =
        GenericRecyclerBindingAdapter<Businesses>(R.layout.fragment_main_item_list)

    private val _businessesResult =
        MutableStateFlow<ViewStateResult<BusinessesResponse?>>(ViewStateResult.Loading)
    private val businessesResult: StateFlow<ViewStateResult<BusinessesResponse?>> =
        _businessesResult
    private val _businessesResultsFromDB =
        MutableStateFlow<ViewStateResult<List<Businesses?>>>(ViewStateResult.Loading)
    private val businessesResultsFromDB: StateFlow<ViewStateResult<List<Businesses?>>> =
        _businessesResultsFromDB

    private val selectionAction =
        object : GenericRecyclerBindingAdapter.OnItemClickListener<Businesses> {
            override fun onItemClick(position: Int, item: Businesses) {
                item
            }
        }

    init {
        viewModelScope.launch {
            businessesResult.collectLatest {
                it.onSuccess { data ->
                    _loading.value = false
                    data?.businesses?.let { it1 -> repository.updateBusinessesDB(it1) }
                    setBusinessesData(data)
                }.onError {
                    getBusinessesDataFromDB()
                }.onLoading {
                    _loading.value = true
                }
            }
        }
        viewModelScope.launch {
            businessesResultsFromDB.collectLatest {
                it.onSuccess { data ->
                    _loading.value = false
                    if (data != null) {
                        setBusinessesDataFromDB(data)
                    }
                }
                    .onError { error ->
                        _triggerErrorDialog.value = error.messageResource.toString()
                    }.onLoading {
                        _loading.value = true
                    }
            }
        }
    }

    private fun getBusinessesDataFromDB() =
        viewModelScope.launch(dispatcher.Default) {
            repository.getBusinessesDB().collect {
                _businessesResultsFromDB.emit(it)
            }
        }

    private fun getBusinessesData(latitude: Double?, longitude: Double?, term: String?) =
        viewModelScope.launch(dispatcher.IO) {
            safeLet(latitude, longitude, term) { latitude, longitude, term ->
                repository.getBusinesses(latitude, longitude, term).collect {
                    _businessesResult.emit(it)
                }
            }
        }

    private fun setBusinessesData(data: BusinessesResponse?) {
        businessesListAdapter.setOnItemClickListener(selectionAction)
        businessesList = arrayListOf()
        (data?.businesses as java.util.ArrayList<Businesses>).map {
            businessesMap.put(it.id, it)
        }
        businessesList.addAll(businessesMap.values)
        businessesListAdapter.submitList(businessesList)
    }

    private fun setBusinessesDataFromDB(data: List<Businesses?>) {
        businessesListAdapter.submitList(data as ArrayList<Businesses>)
    }

    fun getData(latitude: Double? = null, longitude: Double? = null) {
        businessesMap.clear()
        getBusinessesData(
            latitude,
            longitude,
            "pizza"
        )
        getBusinessesData(
            latitude,
            longitude,
            "beer"
        )
    }
}