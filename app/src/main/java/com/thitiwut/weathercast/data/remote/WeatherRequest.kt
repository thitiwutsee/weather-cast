package com.thitiwut.weathercast.data.remote

data class WeatherRequest(
    val city: String? = null,
    val country: String? = null,
    val units: String = "metric",
)