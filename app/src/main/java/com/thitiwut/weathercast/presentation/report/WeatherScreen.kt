package com.thitiwut.weathercast.presentation.report

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import com.thitiwut.weathercast.ui.theme.darkNavyBlue
import com.thitiwut.weathercast.ui.theme.darkSteelBlue
import com.thitiwut.weathercast.ui.theme.lightGrayish
import com.thitiwut.weathercast.ui.theme.lightIndigo
import com.thitiwut.weathercast.ui.theme.lightYellow
import com.thitiwut.weathercast.ui.theme.skyBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherScreen(isDaytime: Boolean = true) {
    Scaffold {
        DayAndNightBackground(isDaytime) {
            Column {
                WeatherCurrentInfoLayout()
                WeatherDetailInfoLayout(isDaytime)
            }
        }
    }
}

@Composable
fun DayAndNightBackground(isDaytime: Boolean, content: @Composable () -> Unit) {
    val backgroundBrush = if (isDaytime) {
        Brush.verticalGradient(
            colors = listOf(
                darkSteelBlue,
                skyBlue,
                lightYellow.copy(alpha = 0.3f)
            )
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(
                darkNavyBlue,
                lightIndigo,
                lightGrayish
            )
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(brush = backgroundBrush)
    ) {
        content()
    }
}


@Preview(
    name = "Pixel 7a",
    showBackground = true,
    device = "spec:shape=Normal,width=1080,height=2400,unit=px,dpi=440"
)
@Composable
fun WeatherScreenDailyLightPreview() {
    WeatherScreen()
}

@Preview(
    name = "Pixel 7a",
    showBackground = true,
    device = "spec:shape=Normal,width=1080,height=2400,unit=px,dpi=440"
)
@Composable
fun WeatherScreenDarkPreview() {
    WeatherScreen(false)
}