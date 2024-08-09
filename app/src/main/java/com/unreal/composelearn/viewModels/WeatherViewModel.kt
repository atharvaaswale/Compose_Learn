package com.unreal.composelearn.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unreal.composelearn.retrofit.RetrofitInstance
import com.unreal.composelearn.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel: ViewModel() {
    val api = RetrofitInstance.apiService
    fun getWeatherData(city: String) {
        Log.i("1212Test", "getWeatherData: $city")
        /*CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                val response = api.getWeather(Constants.apiKey, city)
                if (response.isSuccessful) {
                    val weatherData = response.body()
                    Log.d("1212Test", "Weather data: ${weatherData!!.current.condition}")
                } else {
                    Log.d("1212Test", response.message())
                }
            }
        }*/
        viewModelScope.launch {
            val response = api.getWeather(Constants.apiKey, city)
            if (response.isSuccessful) {
                val weatherData = response.body()
                Log.d("1212Test", "Weather data: ${weatherData!!.current.condition}")
            } else {
                Log.d("1212Test", response.message())
            }
        }
    }
}