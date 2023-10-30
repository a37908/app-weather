package com.example.appweather.api

import com.example.appweather.WeatherModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repository {
    companion object {
        suspend fun getDataWeather(): Flow<List<WeatherModel>> = flow {
            val response = ApiService.api.fetchDataWeather()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}