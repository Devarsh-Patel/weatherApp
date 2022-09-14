package com.example.weatherapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.API
import com.example.weatherapp.model.CurrentObservation
import com.example.weatherapp.model.Forecasts
import com.example.weatherapp.model.Location
import com.example.weatherapp.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "CitiesViewModel"
class CitiesViewModel: ViewModel() {

    private val _cities = MutableLiveData<Location>()

    val cities: MutableLiveData<Location>
     get() = _cities


    private val _currentObservation = MutableLiveData<CurrentObservation>()

    val currentObservation: MutableLiveData<CurrentObservation>
        get() = _currentObservation

    private val _forecasts = MutableLiveData<List<Forecasts>>()

    val forecasts : MutableLiveData<List<Forecasts>>
        get() = _forecasts

    private val _errorMessages = MutableLiveData("")

    val errorMessages: LiveData<String>
        get() = _errorMessages


    fun getCities(city: String){
        API.weatherApi.weatherCityByName(city)
            .enqueue(
                object: Callback<WeatherData> {

                    override fun onResponse(
                        call: Call<WeatherData>,
                        response: Response<WeatherData>) {

                        if(response.isSuccessful){
                            response.body()?.let {
                                _cities.value = it.location
                                _currentObservation.value = it.current_observation
                                _forecasts.value = it.forecasts
                                Log.d(TAG, "onResponse:::::::::::::::::::::: ${it.location}")
                            } ?: kotlin.run {
                                _errorMessages.value = response.message()
                            }
                        }
                        else{
                            _errorMessages.value = response.message()
                        }
                    }


                    override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                        t.printStackTrace()
                        _errorMessages.value = t.message ?: "Unknown error"
                    }

                }
            )
    }
}