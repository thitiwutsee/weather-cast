package com.thitiwut.weathercast.presentation.report.data

import com.thitiwut.weathercast.data.remote.Hour

data class WeatherDetailInfo(
    val pressure: Int,
    val humidity: Int,
    val visibility: Int,
    val windSpeed: Double,
    val rain:Hour?=null,
    val snow:Hour?=null,
    val sunrise: String,
    val sunset: String,
)
