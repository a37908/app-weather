package com.example.appweather.api

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appweather.WeatherModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ApiViewModel : ViewModel() {
    fun weatherModelViewModel(): MutableLiveData<List<WeatherModel>> {
        val responseLiveData: MutableLiveData<List<WeatherModel>> = MutableLiveData()
        viewModelScope.launch {
            Repository.getDataWeather()
                .catch { e ->
                    Log.e("api", "weatherViewModel: ${e.message}")
                }.collect { response ->
                    responseLiveData.value = response
                }
        }
        return responseLiveData
    }
}