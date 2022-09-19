package com.example.weatherapp.model

data class CurrentObservation (
    val wind: Wind,
    val atmosphere: Atmosphere,
    val astronomy: Astronomy,
    val condition: Condition
    )

data class Wind(
    val chill: Int,
    val direction: Int,
    val speed: Int
)

data class Atmosphere(
    val humidity: Int,
    val visibility: Double,
    val pressure: Double
)

data class Astronomy(
    val sunrise: String,
    val sunset: String
)

data class Condition(
    val code: Int,
    val text: String,
    val temperature: Int
)