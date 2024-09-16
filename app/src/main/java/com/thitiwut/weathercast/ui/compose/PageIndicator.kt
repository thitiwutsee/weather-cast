package com.thitiwut.weathercast.ui.compose

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thitiwut.weathercast.ui.theme.textColorDay
import com.thitiwut.weathercast.ui.theme.textColorNight

@Composable
fun IndicatorDots(isSelected: Boolean, modifier: Modifier,color: Color) {
    val size = animateDpAsState(targetValue = if (isSelected) 12.dp else 10.dp, label = "")
    Box(modifier = modifier
        .padding(2.dp)
        .size(size.value)
        .clip(CircleShape)
        .background(if (isSelected) color else color.copy(alpha = 0.5f))
    )
}

@Composable
fun PageIndicator(
    count: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    color: Color
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ){
        for (i in 0 until count) {
            IndicatorDots(isSelected = i == currentPage, modifier = Modifier,color)
        }
    }
}

@Preview
@Composable
fun PageIndicatorDayPreview() {
    PageIndicator(count = 5, currentPage = 2, Modifier,textColorDay)
}

@Preview
@Composable
fun PageIndicatorDarkPreview() {
    PageIndicator(count = 5, currentPage = 2, Modifier, textColorNight)
}