package com.example.maxweather.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.maxweather.data.mappers.toWeatherInfo
import com.example.maxweather.data.remote.WeatherApi
import com.example.maxweather.domain.Resource
import com.example.maxweather.domain.repository.WeatherRepository
import com.example.maxweather.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(lat, long)
                    .toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "A unknown error occurred.")
        }
    }
}