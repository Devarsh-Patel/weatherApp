package com.example.weatherapp.data

import com.example.weatherapp.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET(GET_CITY)
    fun weatherCityByName(
        @Query(ARG_SEARCH) searchInput: String
    ): Call<WeatherData>
}