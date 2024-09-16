package com.thitiwut.weathercast.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thitiwut.weathercast.data.screen.Screen
import com.thitiwut.weathercast.presentation.report.WeatherScreen
import com.thitiwut.weathercast.presentation.report.viewModel.WeatherViewModel
import com.thitiwut.weathercast.ui.compose.HideSystemBars
import com.thitiwut.weathercast.ui.theme.WeatherCastTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherCastTheme {
                WeatherCastApp()
            }
        }
    }
}


@Composable
private fun WeatherCastApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.WeatherReport) {
        composable<Screen.WeatherReport> {
            HideSystemBars()
            val weatherViewModel: WeatherViewModel = hiltViewModel()
            val weatherState by weatherViewModel.weatherState.collectAsState()
            val searchText by weatherViewModel.searchText.collectAsState()
            val isDaytime by weatherViewModel.isDayTime.collectAsState()
            val history by weatherViewModel.history.collectAsState()

            WeatherScreen(
                isDaytime,
                weatherState = weatherState,
                searchText = searchText,
                onSearchTextChange = { weatherViewModel.setSearchText(it) },
                onSearchClick = {
                    weatherViewModel.getWeather()
                },
                history = history,
                onRefresh = {
                    weatherViewModel.refreshWeather()
                }
            )
        }

    }
}

