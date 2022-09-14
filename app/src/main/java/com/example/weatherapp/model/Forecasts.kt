package com.example.weatherapp.model

data class Forecasts (
    val day: String,
    val date: Int,
    val low: Int,
    val high: Int,
    val text: String,
    val code: Int
        )