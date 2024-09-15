package com.thitiwut.weathercast.presentation.report.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thitiwut.weathercast.data.WeatherRepository
import com.thitiwut.weathercast.data.local.LocalHistory
import com.thitiwut.weathercast.data.remote.WeatherRequest
import com.thitiwut.weathercast.di.IoDispatcher
import com.thitiwut.weathercast.presentation.report.WeatherState
import com.thitiwut.weathercast.util.isDaytime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Initial)
    val weatherState = _weatherState.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isDayTime = MutableStateFlow(isDaytime(System.currentTimeMillis()))
    val isDayTime = _isDayTime.asStateFlow()

    private val _history = MutableStateFlow<List<LocalHistory>>(
        emptyList()
    )
    val history = _history.asStateFlow()

    init {
        getHistory()
    }

    private val errorHandler = CoroutineExceptionHandler { _, exception ->

        viewModelScope.launch {
            if (_searchText.value.isNotBlank()) {
                repository.addHistory(
                    LocalHistory(
                        if (_history.value.isEmpty()) 0 else repository.getMaxId() + 1,
                        _searchText.value,
                        false
                    )
                )
            }

        }

        when (exception) {
            is HttpException -> {
                _weatherState.value = WeatherState.Error(
                    code = exception.code(),
                    message = exception.message ?: "Unknown error"
                )
            }

            is IOException -> {
                _weatherState.value = WeatherState.Error(
                    code = 500,
                    message = exception.message ?: "Unknown error"
                )
            }

            else -> {
                _weatherState.value = WeatherState.Error(
                    code = 500,
                    message = exception.message ?: "Unknown error"
                )
            }
        }
    }

    fun getWeather() {
        viewModelScope.launch(dispatcher + errorHandler) {
            _weatherState.value = WeatherState.Loading
            val city = _searchText.value

            val response = repository.getWeather(
                WeatherRequest(
                    city = city,
                )
            )
            _isDayTime.value = isDaytime(response.dt.toLong(), response.timezone)
            _weatherState.value = WeatherState.Success(response)
            _searchText.update {
                if (city.isNotBlank()) {
                    repository.addHistory(
                        LocalHistory(
                            if (_history.value.isEmpty()) 0 else repository.getMaxId() + 1,
                            city,
                            true
                        )
                    )
                    getHistory()
                }
                ""
            }
        }

    }

    fun setSearchText(text: String) {
        _searchText.value = text
    }

    private fun getHistory() {
        viewModelScope.launch(dispatcher) {
            val response = repository.getHistory()
            _history.value = response
        }
    }


}