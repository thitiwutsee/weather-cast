package com.thitiwut.weathercast.data

import com.thitiwut.weathercast.data.local.HistoryDao
import com.thitiwut.weathercast.data.local.LocalHistory
import com.thitiwut.weathercast.data.remote.WeatherCastApiService
import com.thitiwut.weathercast.data.remote.WeatherRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherCastApiService,
    private val historyDao: HistoryDao
) {
    suspend fun getWeather(request: WeatherRequest) = weatherApiService.getWeather(
        cityName = "${request.city}${request.country?.let { ",$it" } ?: ""}",
        units = request.units
    )

    suspend fun getHistory() = historyDao.getAll()

    suspend fun addHistory(history: LocalHistory) = historyDao.add(history)

    suspend fun getMaxId() = historyDao.getMaxId()

}