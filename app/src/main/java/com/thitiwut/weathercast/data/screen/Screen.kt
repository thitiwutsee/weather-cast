package com.thitiwut.weathercast.data.screen
import kotlinx.serialization.Serializable

interface Screen {
    @Serializable
    data object WeatherReport : Screen
}