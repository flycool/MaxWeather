package com.example.maxweather.domain.repository

import com.example.maxweather.domain.Resource
import com.example.maxweather.domain.weather.WeatherInfo

interface WeatherRepository {

    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}