package com.example.maxweather.domain.weather

data class WeatherInfo(
    val weatherDataPerDay:Map<Int, List<WeatherData>>,
    val currentWeatherData:WeatherData?,
)
