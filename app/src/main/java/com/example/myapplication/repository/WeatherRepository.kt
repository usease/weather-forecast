package com.example.myapplication.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.AppExecutors
import com.example.myapplication.db.WeatherDatabase
import com.example.myapplication.db.entities.Weather
import com.example.myapplication.network.model.ApiResponse
import com.example.myapplication.network.model.Resource
import com.example.myapplication.network.model.weather_stack.WeatherStackDto
import com.example.myapplication.network.OpenWeatherWebServiceWrapper
import com.example.myapplication.network.WeatherStackWebServiceWrapper
import com.example.myapplication.network.model.open_weather.OpenWeatherDto
import com.example.myapplication.utils.OpenForTesting
import com.example.myapplication.utils.RateLimiter
import java.util.concurrent.TimeUnit

open class WeatherRepository(private val appExecutors: AppExecutors,
                        private val db: WeatherDatabase,
                        private val openWeatherWrapper: OpenWeatherWebServiceWrapper,
                        private val weatherStackWrapper: WeatherStackWebServiceWrapper) {

    private val repoListRateLimit = RateLimiter<String>(10, TimeUnit.SECONDS)

    fun getCurrentWeatherFor(query: String): LiveData<Resource<Weather>> {
        return object : NetworkBoundResource<Weather, WeatherStackDto>(appExecutors) {
            override fun saveCallResult(item: WeatherStackDto) {
                db.weatherDao().insertAll(weatherStackWrapper.mapper.mapToDomainModel(item))
            }

            override fun shouldFetch(data: Weather?): Boolean {
                return data == null || repoListRateLimit.shouldFetch(query)
            }

            override fun loadFromDb(): LiveData<Weather> {
                return db.weatherDao().getLast()
            }

            override fun createCall(): LiveData<ApiResponse<WeatherStackDto>> {
                return weatherStackWrapper.webService.getCurrentWeatherFor(query)
            }

            override fun onFetchFailed() {
                getOpenWeatherCurrentWeatherFor(query)
            }

        }.asLiveData()
    }

    private fun getOpenWeatherCurrentWeatherFor(query: String): LiveData<Resource<Weather>> {
        return object : NetworkBoundResource<Weather, OpenWeatherDto>(appExecutors) {
            override fun saveCallResult(item: OpenWeatherDto) {
                db.weatherDao().insertAll(openWeatherWrapper.mapper.mapToDomainModel(item))
            }

            override fun shouldFetch(data: Weather?): Boolean {
                return data == null || repoListRateLimit.shouldFetch(query)
            }

            override fun loadFromDb(): LiveData<Weather> {
                return db.weatherDao().getLast()
            }

            override fun createCall(): LiveData<ApiResponse<OpenWeatherDto>> {
                return openWeatherWrapper.webService.getCurrentWeatherFor(query)
            }

            override fun onFetchFailed() {
                repoListRateLimit.reset(query)
            }
        }.asLiveData()
    }
}