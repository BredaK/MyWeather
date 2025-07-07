package com.example.myweather.data.repository

import com.example.myweather.data.model.WeatherResponse
import com.example.myweather.data.network.WeatherApiService

class WeatherRepository(
    private val api: WeatherApiService
) {
    suspend fun getWeather(lat: Double, lon: Double): WeatherResponse {
        return api.getCurrentWeather(lat, lon)
    }
}

