package com.example.weatherapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.data.API
import com.example.weatherapp.model.City
import com.example.weatherapp.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitiesViewModel: ViewModel() {

    private val _cities = MutableLiveData<List<City?>?>()

    val cities: MutableLiveData<List<City?>?>
     get() = _cities

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
                                _cities.value = it.cities
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