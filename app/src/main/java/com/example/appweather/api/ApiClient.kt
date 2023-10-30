package com.example.appweather.api

import com.example.appweather.WeatherModel
import retrofit2.http.GET

interface ApiClient {
    @GET("api/Playlist/json/vnews_weather?fbclid=IwAR2V9h052GhKXXsUG5tnVU3N3Sl5zxcgd_n-ExhTB9mr8scSeNnWuolyYw4")
    suspend fun fetchDataWeather(): List<WeatherModel>
}