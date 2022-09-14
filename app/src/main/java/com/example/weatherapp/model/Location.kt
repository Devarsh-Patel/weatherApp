package com.example.weatherapp.model

data class Location (
    val city: String,
    val region: String,
    val woeid: Int,
    val country: String,
    val lat: Double,
    val long:Double,
    val timezone_id:String
        )