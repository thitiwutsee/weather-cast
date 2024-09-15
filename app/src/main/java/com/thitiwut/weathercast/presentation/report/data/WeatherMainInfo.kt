package com.thitiwut.weathercast.presentation.report.data

data class WeatherMainInfo(
    val main: String,
    val description:String,
    val iconUrl:String,
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double,
)
