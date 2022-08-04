package com.example.ineedpizzabeer.presentation.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.ineedpizzabeer.databinding.FragmentMainBinding
import com.example.ineedpizzabeer.presentation.ui.view.MainActivity
import com.example.ineedpizzabeer.presentation.viewModel.MainViewModel
import com.example.ineedpizzabeer.utils.CustomDialog
import com.example.ineedpizzabeer.utils.isLocationPermissionGranted
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (isLocationPermissionGranted(requireActivity())) {
            getLastLocation()
        } else {
            viewModel.getData()
        }
        initObserver()
    }

    private fun initObserver() {
        lifecycleScope.launchWhenStarted {
            viewModel.loading.collectLatest {
                if (it) {
                    (requireActivity() as? MainActivity)?.binding?.progressBar2?.visibility =
                        View.VISIBLE
                } else {
                    (requireActivity() as? MainActivity)?.binding?.progressBar2?.visibility =
                        View.GONE
                }
            }

            viewModel.triggerErrorDialog.collectLatest {
                context?.let { mContext ->
                    CustomDialog(mContext).show(
                        "Error",
                        it
                    )
                }
            }
        }
    }

    private fun getLastLocation() {
        val fusedLocationClient: FusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(requireActivity())
        fusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful && task.result != null) {
                task.result.let {
                    viewModel._latitude.value = it.latitude
                    viewModel._longitude.value = it.longitude
                }
            }
        }
    }
}