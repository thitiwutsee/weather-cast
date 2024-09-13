package com.thitiwut.weathercast.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.thitiwut.weathercast.presentation.report.WeatherScreen
import com.thitiwut.weathercast.ui.theme.WeatherCastTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherCastTheme {
               WeatherScreen()
            }
        }
    }
}

