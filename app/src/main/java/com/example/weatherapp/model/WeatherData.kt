package com.example.weatherapp.model



data class WeatherData(
    val location: Location,
    val current_observation: CurrentObservation,
    val forecasts: List<Forecasts>
)