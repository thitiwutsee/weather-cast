package com.thitiwut.weathercast.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherCastApiService {

    @GET("weather")
    suspend fun getWeather(
        @Query("q") cityName: String,
        @Query("units") units: String,
    ): WeatherResponse
}