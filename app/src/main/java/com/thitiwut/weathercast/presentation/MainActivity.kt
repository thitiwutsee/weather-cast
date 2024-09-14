package com.thitiwut.weathercast.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.thitiwut.weathercast.presentation.report.WeatherScreen
import com.thitiwut.weathercast.ui.theme.WeatherCastTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherCastTheme {
               WeatherScreen()
            }
        }
    }
}

