package com.example.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.databinding.ListWeatherDetailsBinding
import com.example.weatherapp.viewModel.CitiesViewModel


class CitiesFragment: Fragment() {

    private lateinit var binding: ListWeatherDetailsBinding
    private val viewModel: CitiesViewModel by lazy {
        ViewModelProvider(this)[CitiesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = ListWeatherDetailsBinding.inflate(inflater,container, false)

        viewModel.getCities("Cullman")

        viewModel.cities.observe(viewLifecycleOwner){
            binding.txtPrint.text = it.toString()
        }

        return binding.root
    }
}