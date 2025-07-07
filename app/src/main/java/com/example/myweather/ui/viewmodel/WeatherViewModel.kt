package com.example.myweather.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.data.model.WeatherResponse
import com.example.myweather.data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


sealed class WeatherUiState {
    object Loading : WeatherUiState()
    data class Success(val data: WeatherResponse) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {


    private val _weatherState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val weatherState: StateFlow<WeatherUiState> = _weatherState

    fun fetchWeather(lat: Double, lon: Double) {
        viewModelScope.launch {
            _weatherState.value = WeatherUiState.Loading
            try {
                val response = repository.getWeather(lat, lon)
                _weatherState.value = WeatherUiState.Success(response)
            } catch (e: Exception) {
                _weatherState.value = WeatherUiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }
}
