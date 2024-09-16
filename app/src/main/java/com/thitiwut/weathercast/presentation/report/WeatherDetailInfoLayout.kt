package com.thitiwut.weathercast.presentation.report

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thitiwut.weathercast.R
import com.thitiwut.weathercast.data.remote.Hour
import com.thitiwut.weathercast.presentation.report.data.DetailValueType
import com.thitiwut.weathercast.presentation.report.data.WeatherDetailInfo
import com.thitiwut.weathercast.ui.compose.PageIndicator
import com.thitiwut.weathercast.ui.compose.shimmerEffect
import com.thitiwut.weathercast.ui.theme.WeatherCastTheme
import com.thitiwut.weathercast.ui.theme.bgCardDaily
import com.thitiwut.weathercast.ui.theme.bgCardNightly
import com.thitiwut.weathercast.ui.theme.textColorDay
import com.thitiwut.weathercast.ui.theme.textColorNight

@Composable
fun WeatherDetailInfoLayout(
    isLoading: Boolean,
    isDaytime: Boolean,
    detailInfo: WeatherDetailInfo? = null
) {

    WeatherEventLayout(isLoading, isDaytime, detailInfo)

    Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)) {
        WeatherDetailItem(
            Modifier
                .fillMaxWidth()
                .weight(1f), isDaytime, false,
            DetailValueType.PRESSURE.icon,
            DetailValueType.PRESSURE.title,
            "",
            "${detailInfo?.pressure ?: 0} ${DetailValueType.PRESSURE.unit}",
            isLoading

        )
        Spacer(modifier = Modifier.size(8.dp))
        WeatherDetailItem(
            Modifier
                .fillMaxWidth()
                .weight(1f), isDaytime,
            false,
            DetailValueType.HUMIDITY.icon,
            DetailValueType.HUMIDITY.title,
            "",
            "${detailInfo?.humidity ?: 0} ${DetailValueType.HUMIDITY.unit}",
            isLoading
        )
    }
    Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 8.dp)) {
        WeatherDetailItem(
            Modifier
                .fillMaxWidth()
                .weight(1f), isDaytime, false,
            DetailValueType.VISIBILITY.icon,
            DetailValueType.VISIBILITY.title,
            "",
            "${detailInfo?.visibility ?: 0} ${DetailValueType.VISIBILITY.unit}",
            isLoading

        )
        Spacer(modifier = Modifier.size(8.dp))
        WeatherDetailItem(
            Modifier
                .fillMaxWidth()
                .weight(1f), isDaytime,
            false,
            DetailValueType.WIND_SPEED.icon,
            DetailValueType.WIND_SPEED.title,
            "",
            "${detailInfo?.windSpeed ?: 0} ${DetailValueType.WIND_SPEED.unit}",
            isLoading
        )
    }
    //TODO Sunrise and Sunset
    WeatherDetailItem(
        Modifier
            .fillMaxWidth()
            .padding(16.dp), isDaytime, true,
        R.drawable.ic_outline_rainy_24,
        "Rainy",
        "Rainy",
        "22mm",
        isLoading
    )
}

@Composable
fun WeatherEventLayout(
    isLoading: Boolean,
    isDaytime: Boolean,
    detailInfo: WeatherDetailInfo? = null
) {
    if (!isLoading) {
        val weatherEvent = mutableListOf<Pair<DetailValueType, Hour>>()
        detailInfo?.rain?.let {
            weatherEvent.add(Pair(DetailValueType.RAIN, it))
        }
        detailInfo?.snow?.let {
            weatherEvent.add(Pair(DetailValueType.SNOW, it))
        }
        if (weatherEvent.isNotEmpty()) {
            val pagerState = rememberPagerState(pageCount = { weatherEvent.size }, initialPage = 0)
            val textColor = if (isDaytime) {
                textColorDay
            } else {
                textColorNight
            }
            Column {
                HorizontalPager(
                    state = pagerState
                ) { page ->
                    WeatherDetailItem(
                        Modifier
                            .fillMaxWidth()
                            .padding(16.dp), isDaytime, true,
                        weatherEvent[page].first.icon,
                        weatherEvent[page].first.title,
                        "${weatherEvent[page].first.title} volume in 1 hour",
                        "${weatherEvent[page].second.oneHour} ${DetailValueType.RAIN.unit}",
                        isLoading
                    )
                }

                if (weatherEvent.size > 1) {
                    PageIndicator(
                        count = weatherEvent.size,
                        currentPage = pagerState.currentPage,
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = textColor
                    )
                }
            }
        }

    }


}

@Composable
fun WeatherDetailItem(
    modifier: Modifier = Modifier,
    isDaytime: Boolean,
    isHighlight: Boolean = false,
    icon: Int,
    title: String,
    desc: String,
    value: String,
    isLoading: Boolean,
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
    if (isLoading) {

        Box(
            modifier = modifier
                .height(80.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .shimmerEffect()
        )
    } else {
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
                            colorFilter = ColorFilter.tint(textColor.copy(0.5f))
                        )
                        Text(
                            modifier = Modifier
                                .padding(top = 16.dp)
                                .align(Alignment.CenterVertically),
                            fontSize = 14.sp,
                            fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                            fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                            text = title,
                            color = textColor.copy(0.5f)
                        )
                    }

                    Text(
                        modifier = Modifier.padding(16.dp),
                        fontSize = if (isHighlight) 16.sp else 20.sp,
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

}

@Preview
@Composable
fun WeatherDetailItemDailyLoadingPreview() {
    WeatherCastTheme {
        WeatherDetailItem(
            Modifier
                .fillMaxWidth()
                .padding(16.dp), true,
            true,
            R.drawable.ic_outline_rainy_24,
            "Rainy",
            "Rainy",
            "22mm", true
        )
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
            "22mm",
            false
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
            DetailValueType.VISIBILITY.title,
            "0 ${DetailValueType.VISIBILITY.unit}",
            "",
            false
        )
    }
}

@Preview
@Composable
fun WeatherDetailItemLoadingPreview() {
    val weatherEvent = mutableListOf<Pair<DetailValueType, Hour>>()
    weatherEvent.add(Pair(DetailValueType.RAIN, Hour(0.0, 0.0)))
    weatherEvent.add(Pair(DetailValueType.SNOW, Hour(0.0, 0.0)))

    val pagerState = rememberPagerState(pageCount = { weatherEvent.size }, initialPage = 0)
    val isDaytime = true
    val textColor = if (isDaytime) {
        textColorDay
    } else {
        textColorNight
    }
    Column {
        HorizontalPager(
            state = pagerState
        ) { page ->
            WeatherDetailItem(
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp), isDaytime, true,
                weatherEvent[page].first.icon,
                weatherEvent[page].first.title,
                "${weatherEvent[page].first.title} volume in 1 hour",
                "${weatherEvent[page].second.oneHour} ${DetailValueType.RAIN.unit}",
                false
            )
        }

        if (weatherEvent.size > 1) {
            PageIndicator(
                count = weatherEvent.size,
                currentPage = pagerState.currentPage,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                color = textColor
            )
        }
    }
}
