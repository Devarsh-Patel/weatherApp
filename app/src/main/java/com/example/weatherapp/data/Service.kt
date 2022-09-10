package com.example.weatherapp.data

import com.example.weatherapp.model.WeatherData
import retrofit2.Call
import retrofit2.http.GET

interface Service {
    @GET(GET_CITY)
    suspend fun weatherCityByName(): WeatherData

}