package com.unreal.composelearn.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.unreal.composelearn.IOmodel.WeatherResponse
import com.unreal.composelearn.retrofit.NetworkResponse
import com.unreal.composelearn.retrofit.RetrofitInstance
import com.unreal.composelearn.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class WeatherViewModel: ViewModel() {
    private val api = RetrofitInstance.apiService
    private val _weatherResult = MutableLiveData<NetworkResponse<WeatherResponse>>()
    val weatherResultList: LiveData<NetworkResponse<WeatherResponse>> = _weatherResult
    fun getWeatherData(city: String) {
        _weatherResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = api.getWeather(Constants.apiKey, city)
                if (response.isSuccessful) {
                    _weatherResult.value = NetworkResponse.Success(response.body()!!)
                } else {
                    _weatherResult.value = NetworkResponse.Error("Failed to load data")
                }
            } catch (e: Exception) {
                _weatherResult.value = NetworkResponse.Error("Failed to load data")
            }
        }
    }
}