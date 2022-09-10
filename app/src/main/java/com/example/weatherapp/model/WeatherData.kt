package com.example.weatherapp.model

data class WeatherData(
    var cities: List<City?>?,
    var startIndex: Int?, // 0
    var totalCitiesFound: Int? // 41
)