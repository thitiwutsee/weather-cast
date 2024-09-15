package com.thitiwut.weathercast.presentation.report

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.thitiwut.weathercast.presentation.report.data.WeatherMainInfo
import com.thitiwut.weathercast.ui.compose.shimmerEffect
import kotlin.math.roundToInt

@Composable
fun WeatherCurrentInfoLayout(isLoading: Boolean, mainInfo: WeatherMainInfo? = null) {
    Row(Modifier.fillMaxWidth()) {
        if (isLoading) {
            Column(
                Modifier
                    .weight(1f)
                    .padding(24.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(30.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(20.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(4.dp))
                Box(
                    modifier = Modifier
                        .width(60.dp)
                        .height(14.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .shimmerEffect()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .width(100.dp)
                        .height(14.dp)
                        .clip(shape = RoundedCornerShape(8.dp))
                        .shimmerEffect()
                )
            }
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .size(80.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .shimmerEffect()
            )

        } else {
            mainInfo?.let {
                Column(
                    Modifier
                        .weight(1f)
                        .padding(24.dp)
                ) {
                    Text(
                        fontSize = 24.sp,
                        lineHeight = 32.sp,
                        fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                        fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                        color = Color.White,
                        text = "${mainInfo.temp.roundToInt()}°"
                    )
                    Text(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                        color = Color.White,
                        text = mainInfo.main
                    )
                    Text(
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                        color = Color.White,
                        text = mainInfo.description
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        fontSize = 12.sp,
                        lineHeight = 20.sp,
                        fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                        color = Color.White,
                        text = "${mainInfo.tempMax.roundToInt()}°/${mainInfo.tempMin.roundToInt()}° Feels like ${mainInfo.feelsLike.roundToInt()}°"
                    )
                }
                AsyncImage(
                    modifier = Modifier
                        .padding(16.dp)
                        .size(80.dp),
                    model = mainInfo.iconUrl,
                    contentDescription = "Weather icon",
                )
            }

        }

    }
}

@Preview
@Composable
fun WeatherCurrentInfoLayoutLoadingPreview() {
    WeatherCurrentInfoLayout(true)
}

@Preview
@Composable
fun WeatherCurrentInfoLayoutPreview() {
    val data = WeatherMainInfo(
        main = "Clouds",
        description = "broken clouds",
        iconUrl = "https://openweathermap.org/img/wn/04d.png",
        temp = 25.0,
        feelsLike = 25.0,
        tempMin = 25.0,
        tempMax = 25.0
    )
    WeatherCurrentInfoLayout(false,data)
}

