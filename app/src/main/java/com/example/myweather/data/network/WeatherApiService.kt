package com.example.myweather.data.network

import com.example.myweather.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("weather")
    suspend fun getCurrentWeather(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") apiKey: String = "472469cc0793351fbb22818dfb311458",
        @Query("units") units: String = "metric"
    ): WeatherResponse
}
