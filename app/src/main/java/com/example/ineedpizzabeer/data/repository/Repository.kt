package com.example.ineedpizzabeer.data.repository

import com.example.ineedpizzabeer.domain.model.Businesses
import com.example.ineedpizzabeer.domain.model.BusinessesResponse
import com.example.ineedpizzabeer.utils.ViewStateResult
import com.example.ineedpizzabeer.utils.onFlowStarts
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val repository: IRepositoryHelper
) : IRepository {
    override fun getBusinesses(
        lat: Double,
        lon: Double,
        termSearch: String
    ): Flow<ViewStateResult<BusinessesResponse?>> =
        flow {
            repository.getBusinesses(lat, lon, termSearch).run {
                when (this) {
                    is ViewStateResult.Success -> {
                        data?.let { emit(ViewStateResult.Success(it)) }
                    }
                    is ViewStateResult.Error -> {
                        emit(ViewStateResult.Error(exception))
                    }
                    else -> {
                        emit(ViewStateResult.Loading)
                    }
                }
            }
        }.onFlowStarts()

    override fun getBusinessesDB(): Flow<ViewStateResult<List<Businesses?>>> =
        flow {
            repository.getBusinessesDB().run {
                when (this) {
                    is ViewStateResult.Success -> {
                        emit(ViewStateResult.Success(data))
                    }
                    is ViewStateResult.Error -> {
                        emit(ViewStateResult.Error(exception))
                    }
                    else -> {
                        emit(ViewStateResult.Loading)
                    }
                }
            }
        }.onFlowStarts()

    override suspend fun updateBusinessesDB(list: List<Businesses>) {
        repository.updateBusinessesDB(list)
    }
}