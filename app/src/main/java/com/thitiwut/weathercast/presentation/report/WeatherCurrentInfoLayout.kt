package com.thitiwut.weathercast.presentation.report

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.thitiwut.weathercast.R

@Composable
fun WeatherCurrentInfoLayout() {
    Row(Modifier.fillMaxWidth()) {
        Column(
            Modifier.weight(1f)
                .padding(24.dp)
        ) {
            Text(
                fontSize = 24.sp,
                lineHeight = 32.sp,
                fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                color = Color.White,
                text = "26°"
            )
            Text(
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                color = Color.White,
                text = "Rain"
            )
            Text(
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                color = Color.White,
                text = "light rain"
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                fontSize = 12.sp,
                lineHeight = 20.sp,
                fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                color = Color.White,
                text = "29°/25° Feels like 36°"
            )
        }
        AsyncImage(
            modifier = Modifier
                .padding(16.dp)
                .size(80.dp)
            ,
            model = "https://openweathermap.org/img/wn/01d@2x.png",
            contentDescription = "Weather icon",
            error = painterResource(id = R.drawable.ic_launcher_foreground),
            placeholder = painterResource(id = R.drawable.ic_launcher_foreground)
        )
    }
}


@Preview
@Composable
fun WeatherCurrentInfoLayoutPreview() {
    WeatherCurrentInfoLayout()
}

