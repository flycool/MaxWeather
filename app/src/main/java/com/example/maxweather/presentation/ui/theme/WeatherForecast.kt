package com.example.maxweather.presentation.ui.theme

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.maxweather.presentation.WeatherState
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecast(
    modifier: Modifier = Modifier,
    state: WeatherState,
) {
    val currentData = state.weatherInfo?.currentWeatherData

    state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
        println("data $data")
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Today",
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))

            val lazyListState = rememberLazyListState()
            val scope = rememberCoroutineScope()

            LazyRow(
                state = lazyListState
            ) {
                items((data)) { weatherData ->
                    HourlyWeatherDisplay(
                        weatherData = weatherData,
                        modifier = Modifier
                            .height(100.dp)
                            .padding(horizontal = 16.dp)
                    )
                }
                scope.launch {
                    val scrollToIndex =
                        if ((data.indexOf(currentData) - 1) < 0) 0 else data.indexOf(currentData) - 1
                    lazyListState.animateScrollToItem(scrollToIndex)
                }
            }
        }
    }
}