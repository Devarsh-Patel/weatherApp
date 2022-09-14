package com.example.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ListWeatherDetailsBinding
import com.example.weatherapp.model.Forecasts
import com.example.weatherapp.model.Location
import com.example.weatherapp.view.adapter.CityAdapter
import com.example.weatherapp.viewModel.CitiesViewModel


class CitiesFragment: Fragment() {

    private lateinit var binding: ListWeatherDetailsBinding
    private val viewModel: CitiesViewModel by lazy {
        ViewModelProvider(this)[CitiesViewModel::class.java]
    }

    private lateinit var adapter: CityAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = ListWeatherDetailsBinding.inflate(inflater,container, false)

        initObservables()
        initView()

        return binding.root
    }

    private fun initView() {
        binding.searchCities.setOnQueryTextListener(
            object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return query?.let {
                        viewModel.getCities(it)
                        true
                    } ?: false
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            }
        )

        adapter = CityAdapter {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
        binding.searchList.adapter = adapter
        binding.searchList.layoutManager= LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    private fun initObservables() {
        viewModel.cities.observe(viewLifecycleOwner){ city ->
            binding.txtCity.text = city.city
            binding.txtCity.isVisible = true
            binding.txtCity.setOnClickListener {
                Toast.makeText(context, city.city, Toast.LENGTH_SHORT).show()
                binding.txtCity.isVisible = false
                binding.txtCityState.text = " ${city.city}, ${city.region}"
            }
        }

        viewModel.forecasts.observe(viewLifecycleOwner){
            updateIt(it)
        }

        viewModel.currentObservation.observe(viewLifecycleOwner){
            binding.txtTemp.text= it.condition.temperature.toString()
        }
    }

    private fun updateIt(it: List<Forecasts>) {
        adapter.submitList(it)
    }
}