package com.example.appweather.models

import com.example.appweather.Currently
import com.example.appweather.Daily

data class WeatherShowModel(
    val icon: Int,
    val nameWeather: String,
    val suggest: String,
)