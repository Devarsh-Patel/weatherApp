package com.example.weatherapp.model

import java.io.Serializable

data class WeatherData(
    var cities: List<City?>?,
    var startIndex: Int?, // 0
    var totalCitiesFound: Int? // 41
):Serializable