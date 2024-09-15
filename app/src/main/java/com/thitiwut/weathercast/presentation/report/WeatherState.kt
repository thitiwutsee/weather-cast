package com.thitiwut.weathercast.presentation.report

import com.thitiwut.weathercast.data.remote.WeatherResponse

interface WeatherState {
    object Initial : WeatherState

    object Loading : WeatherState

    data class Success(
        val weatherResponse: WeatherResponse
    ) : WeatherState

    data class Error(
        val code: Int,
        val message: String
    ) : WeatherState


}