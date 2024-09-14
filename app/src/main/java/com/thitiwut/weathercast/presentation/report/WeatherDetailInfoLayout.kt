package com.thitiwut.weathercast.presentation.report

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thitiwut.weathercast.R
import com.thitiwut.weathercast.ui.theme.WeatherCastTheme
import com.thitiwut.weathercast.ui.theme.bgCardDaily
import com.thitiwut.weathercast.ui.theme.bgCardNightly
import com.thitiwut.weathercast.ui.theme.textColorDay
import com.thitiwut.weathercast.ui.theme.textColorNight

@Composable
fun WeatherDetailInfoLayout(isDaytime: Boolean) {
    WeatherDetailItem(
        Modifier
            .fillMaxWidth()
            .padding(16.dp), isDaytime, true,
        R.drawable.ic_outline_rainy_24,
        "Rainy",
        "Rainy",
        "22mm"
    )
    Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)) {
        WeatherDetailItem(
            Modifier
                .fillMaxWidth()
                .weight(1f), isDaytime, false,
            R.drawable.ic_outline_rainy_24,
            "Rainy",
            "Rainy",
            "22mm"

        )
        Spacer(modifier = Modifier.size(8.dp))
        WeatherDetailItem(
            Modifier
                .fillMaxWidth()
                .weight(1f), isDaytime,
            false,
            R.drawable.ic_outline_rainy_24,
            "Rainy",
            "Rainy",
            "22mm"
        )
    }
    Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)) {
        WeatherDetailItem(
            Modifier
                .fillMaxWidth()
                .weight(1f), isDaytime, false,
            R.drawable.ic_outline_rainy_24,
            "Rainy",
            "Rainy",
            "22mm"

        )
        Spacer(modifier = Modifier.size(8.dp))
        WeatherDetailItem(
            Modifier
                .fillMaxWidth()
                .weight(1f), isDaytime,
            false,
            R.drawable.ic_outline_rainy_24,
            "Rainy",
            "Rainy",
            "22mm"
        )
    }
    WeatherDetailItem(
        Modifier
            .fillMaxWidth()
            .padding(16.dp), isDaytime, true,
        R.drawable.ic_outline_rainy_24,
        "Rainy",
        "Rainy",
        "22mm"
    )
}

@Composable
fun WeatherDetailItem(
    modifier: Modifier = Modifier,
    isDaytime: Boolean,
    isHighlight: Boolean = false,
    icon: Int,
    title: String,
    desc: String,
    value: String
) {
    val cardColor = if (isDaytime) {
        bgCardDaily
    } else {
        bgCardNightly
    }

    val textColor = if (isDaytime) {
        textColorDay
    } else {
        textColorNight
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = cardColor.copy(alpha = 0.5f),
        )
    ) {
        Row {
            Column(Modifier.weight(1f)) {
                Row {
                    Image(
                        painter = painterResource(icon),
                        modifier = Modifier
                            .padding(start = 16.dp, top = 16.dp, end = 4.dp)
                            .size(16.dp)
                            .align(Alignment.CenterVertically),
                        contentDescription = "Weather icon",
                        colorFilter = ColorFilter.tint(textColor)
                    )
                    Text(
                        modifier = Modifier
                            .padding(top = 16.dp)
                            .align(Alignment.CenterVertically),
                        fontSize = 14.sp,
                        fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                        fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                        text = title,
                        color = textColor
                    )
                }

                Text(
                    modifier = Modifier.padding(16.dp),
                    fontSize = 16.sp,
                    fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    text = if (isHighlight) desc else value,
                    color = textColor
                )
            }
            if (isHighlight) {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterVertically),
                    fontSize = 20.sp,
                    fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    text = value,
                    color = textColor
                )
            }

        }


    }
}

@Preview
@Composable
fun WeatherDetailItemDailyPreview() {
    WeatherCastTheme {
        WeatherDetailItem(
            Modifier
                .fillMaxWidth()
                .padding(16.dp), true,
            true,
            R.drawable.ic_outline_rainy_24,
            "Rainy",
            "Rainy",
            "22mm"
        )
    }
}

@Preview
@Composable
fun WeatherDetailItemDarkPreview() {
    WeatherCastTheme {
        WeatherDetailItem(
            Modifier
                .fillMaxWidth()
                .padding(16.dp), false,
            true,
            R.drawable.ic_outline_rainy_24,
            "Rainy",
            "Rainy",
            "22mm"
        )
    }
}