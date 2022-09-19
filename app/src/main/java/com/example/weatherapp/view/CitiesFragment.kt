package com.example.weatherapp.view

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.databinding.ListWeatherDetailsBinding
import com.example.weatherapp.model.Forecasts
import com.example.weatherapp.view.adapter.CityAdapter
import com.example.weatherapp.viewModel.CitiesViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException


private const val TAG = "CitiesFragment"
class CitiesFragment(private val fusedLocationProviderClient: FusedLocationProviderClient):
    Fragment(), OnMapReadyCallback{

    private lateinit var binding: ListWeatherDetailsBinding

    private lateinit var googleMap: GoogleMap

    private lateinit var lastLocation: Location

    private lateinit var cityName: String

    private var yes: Boolean = false

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

        initView()
        initObservables()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.onResume()
        binding.mapView.getMapAsync(this)
    }


    private fun initView() {
        binding.searchCities.setOnQueryTextListener(
            object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return query?.let {
                        viewModel.getCities(it)
                        yes = true
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
            if(yes) {
                binding.txtCity.visibility = View.VISIBLE
            } else {
                binding.txtCityState.text = " ${city.city}, ${city.region}"
            }
            binding.txtCity.setOnClickListener {
                Toast.makeText(context, city.city, Toast.LENGTH_SHORT).show()
                binding.txtCity.visibility = View.GONE
                binding.txtCityState.text = " ${city.city}, ${city.region}"
            }
        }

        viewModel.forecasts.observe(viewLifecycleOwner){
            updateIt(it)
        }

        viewModel.currentObservation.observe(viewLifecycleOwner){
            binding.txtTemp.text= it.condition.temperature.toString()
            binding.txtChill.text = "CHILL: ${it.wind.chill}"
            binding.txtDirection.text = "DIRECTION: ${it.wind.direction}"
            binding.txtSpeed.text = "SPEED: ${it.wind.speed} MPH"
            binding.txtHumidity.text ="HUMIDITY: ${it.atmosphere.humidity}"
            binding.txtVisibility.text = "VISIBILITY: ${it.atmosphere.visibility}"
            binding.txtPressure.text = "PRESSURE: ${it.atmosphere.pressure}"
            binding.txtSunrise.text = "SUNRISE TIME: ${it.astronomy.sunrise}"
            binding.txtSunset.text = "SUNSET TIME: ${it.astronomy.sunset}"
            binding.txtText.text = "SKY: ${it.condition.text}"
            binding.txtTemperature.text = "TEMPERATURE: ${it.condition.temperature}"
        }
    }

    private fun updateIt(it: List<Forecasts>) {
        adapter.submitList(it)
    }


    @SuppressLint("MissingPermission")
    override fun onMapReady(map: GoogleMap) {
        map.uiSettings.isZoomControlsEnabled = true
        map.let {
            googleMap = it
            googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL

            googleMap.isMyLocationEnabled = true
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location ->
                // Got last known location. In some rare situations this can be null.
                // 3
                Log.d(TAG, "onMapReady: ::::::::::::::::: $location")

                if (location != null) {
                    lastLocation = location
                    val currentLatLng = LatLng(location.latitude, location.longitude)
                    cityName = getAddress(currentLatLng)
                    viewModel.getCities(cityName)

                    map.addMarker(MarkerOptions().position(currentLatLng))
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 12f))
                }
            }

        }

    }


    private fun getAddress(latLng: LatLng): String {
        // 1
        val geocoder = Geocoder(context)
        val addresses: List<Address>?
        val address: Address?
        var addressText = ""

        try {
            // 2
            addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            // 3
            if (null != addresses && addresses.isNotEmpty()) {

                address = addresses[0]

                addressText = address.locality

            }
        } catch (e: IOException) {
            Log.e("MapsActivity", e.localizedMessage)
        }

        return addressText
    }

}