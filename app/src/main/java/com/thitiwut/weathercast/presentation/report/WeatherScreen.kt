@file:OptIn(ExperimentalMaterial3Api::class)

package com.thitiwut.weathercast.presentation.report

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.android.gms.maps.model.LatLng
import com.thitiwut.weathercast.data.local.LocalHistory
import com.thitiwut.weathercast.data.remote.dummyData
import com.thitiwut.weathercast.ui.compose.shimmerEffect
import com.thitiwut.weathercast.ui.theme.darkNavyBlue
import com.thitiwut.weathercast.ui.theme.darkSteelBlue
import com.thitiwut.weathercast.ui.theme.lightGrayish
import com.thitiwut.weathercast.ui.theme.lightIndigo
import com.thitiwut.weathercast.ui.theme.lightYellow
import com.thitiwut.weathercast.ui.theme.skyBlue

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherScreen(
    isDaytime: Boolean,
    weatherState: WeatherState,
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchClick: () -> Unit = {},
    history: List<LocalHistory>,
    onRefresh: () -> Unit = {}
) {
    var isSearch by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val pullToRefreshState = rememberPullToRefreshState()
    Scaffold {
        DayAndNightBackground(isDaytime) {
            Column(
                Modifier
                    .pullToRefresh(
                        state = pullToRefreshState,
                        onRefresh = {
                            onRefresh()
                        },
                        isRefreshing = weatherState is WeatherState.Loading
                    )
                    .verticalScroll(scrollState)
            ) {
                when (weatherState) {
                    is WeatherState.Loading -> {
                        LocationItem("") {
                            isSearch = true
                        }
                        WeatherCurrentInfoLayout(true)
                        WeatherDetailInfoLayout(true, isDaytime)
                    }

                    is WeatherState.Success -> {
                        val data = weatherState.weatherResponse
                        val mainInfo = data.mapMainInfo()
                        val detailInfo = data.mapDetailInfo()
                        LocationItem(data.name) {
                            isSearch = true
                        }
                        WeatherCurrentInfoLayout(false, mainInfo = mainInfo)
                        WeatherDetailInfoLayout(
                            false,
                            isDaytime = isDaytime,
                            detailInfo = detailInfo
                        )
                        LocationLayout(LatLng(data.location.lat, data.location.lon))
                    }

                    is WeatherState.Error -> {
                        val error = weatherState.message
                        LocationItem(searchText) {
                            isSearch = true
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = "Error icon",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .align(Alignment.CenterHorizontally)
                                )
                                Text(
                                    text = error,
                                    fontSize = 24.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }

                    else -> {
                        LocationItem("-") {
                            isSearch = true
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Column {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search icon",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .size(60.dp)
                                        .align(Alignment.CenterHorizontally)
                                )
                                Text(
                                    text = "Please enter a location",
                                    fontSize = 24.sp,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }

            }

            if (isSearch) {
                SearchBottomSheet(
                    searchText = searchText,
                    onSearchTextChange = onSearchTextChange,
                    onSearchClick = {
                        isSearch = false
                        onSearchClick()
                    },
                    onDismiss = {
                        isSearch = false
                        onSearchTextChange("")
                    },
                    history = history
                )
            }
        }
    }
}

@Composable
fun SearchBottomSheet(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onDismiss: () -> Unit,
    history: List<LocalHistory>,
) {
    val text = remember { mutableStateOf(searchText) }
    val bottomSheetState = rememberModalBottomSheetState()
    val focusRequester = remember { FocusRequester() }
    val focus = remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    ModalBottomSheet(
        onDismissRequest = {
            onDismiss()
        },
        sheetState = bottomSheetState,
    ) {
        TextField(
            value = text.value,
            onValueChange = {
                text.value = it
                onSearchTextChange(text.value)
            },
            label = { Text("Search Location") },
            maxLines = 1,
            modifier = Modifier
                .focusRequester(focusRequester)
                .fillMaxWidth()
                .padding(16.dp)
                .onFocusChanged {
                    if (focus.value != it.isFocused) {
                        focus.value = it.isFocused
                    }
                },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = {
                onSearchClick()
            })
        )
        if (history.isNotEmpty()) {
            history.forEach {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            text.value = it.searchText
                            onSearchTextChange(it.searchText)
                            onSearchClick()
                        }
                        .padding(16.dp),
                    fontSize = 18.sp,
                    lineHeight = 28.sp,
                    fontStyle = MaterialTheme.typography.titleLarge.fontStyle,
                    fontWeight = MaterialTheme.typography.titleLarge.fontWeight,
                    text = it.searchText
                )
            }

        } else {
            Spacer(modifier = Modifier.height(200.dp))
        }
    }
}

@Composable
fun LocationItem(location: String, isLoading: Boolean = false, onSearchClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 30.dp)
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .height(30.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .shimmerEffect()
            )
        } else {
            Row(
                modifier = Modifier.align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = location,
                    fontSize = 28.sp,
                    lineHeight = 30.sp,
                    fontStyle = MaterialTheme.typography.titleMedium.fontStyle,
                    fontWeight = MaterialTheme.typography.titleMedium.fontWeight,
                    color = Color.White,
                )
                Icon(
                    modifier = Modifier.padding(start = 4.dp),
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location icon",
                    tint = Color.White
                )
            }
        }

        Icon(
            modifier = Modifier
                .size(28.dp)
                .align(Alignment.CenterEnd)
                .clickable {
                    onSearchClick()
                },
            imageVector = Icons.Default.Search,
            contentDescription = "Search icon",
            tint = Color.White
        )
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
fun WeatherScreenDailyLightLoadingPreview() {
    val weatherState = WeatherState.Loading
    WeatherScreen(
        true,
        weatherState = weatherState,
        "",
        {},
        {},
        listOf(LocalHistory(0, "Bangkok", true))
    )
}

@Preview(
    name = "Pixel 7a",
    showBackground = true,
    device = "spec:shape=Normal,width=1080,height=2400,unit=px,dpi=440"
)
@Composable
fun WeatherScreenDarkLoadingPreview() {
    val weatherState = WeatherState.Loading
    WeatherScreen(false, weatherState, "", {}, {}, listOf(LocalHistory(0, "Bangkok", true)))
}

@Preview(
    name = "Pixel 7a",
    showBackground = true,
    device = "spec:shape=Normal,width=1080,height=2400,unit=px,dpi=440"
)
@Composable
fun WeatherScreenDailyLightPreview() {
    val weatherState = WeatherState.Success(dummyData)
    WeatherScreen(
        true,
        weatherState = weatherState,
        "",
        {},
        {},
        listOf(LocalHistory(0, "Bangkok", true))
    )
}

@Preview(
    name = "Pixel 7a",
    showBackground = true,
    device = "spec:shape=Normal,width=1080,height=2400,unit=px,dpi=440"
)
@Composable
fun WeatherScreenDarkPreview() {
    val weatherState = WeatherState.Success(dummyData)
    WeatherScreen(
        false,
        weatherState = weatherState,
        "",
        {},
        {},
        listOf(LocalHistory(0, "Bangkok", true))
    )
}