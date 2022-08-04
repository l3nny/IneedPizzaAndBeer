package com.example.ineedpizzabeer.presentation.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.ineedpizzabeer.R
import com.example.ineedpizzabeer.databinding.ActivityMainBinding
import com.example.ineedpizzabeer.presentation.ui.fragment.MainFragment
import com.example.ineedpizzabeer.presentation.viewModel.MainViewModel
import com.example.ineedpizzabeer.utils.isLocationPermissionGranted
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        setContentView(binding.root)
        isLocationPermissionGranted(this)
        setFragment()
    }

    private fun setFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commitNow()
    }
}