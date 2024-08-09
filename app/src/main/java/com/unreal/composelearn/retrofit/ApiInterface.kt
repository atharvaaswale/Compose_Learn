package com.unreal.composelearn.retrofit

import com.unreal.composelearn.IOmodel.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("/v1/current.json")
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String
    ) : Response<WeatherResponse>
}