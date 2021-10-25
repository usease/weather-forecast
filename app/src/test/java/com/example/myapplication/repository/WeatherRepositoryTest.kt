package com.example.myapplication.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.db.WeatherDatabase
import com.example.myapplication.db.daos.WeatherDao
import com.example.myapplication.network.OpenWeatherWebServiceWrapper
import com.example.myapplication.network.WeatherStackWebServiceWrapper
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import util.InstantAppExecutors
import util.mock

//@RunWith(MockitoJUnitRunner::class)
//class WeatherRepositoryTest {
//    private val db = Mockito.mock(WeatherDatabase::class.java)
//    private val weatherStackWrapper = Mockito.mock(WeatherStackWebServiceWrapper::class.java)
//    private val openWeatherWrapper = Mockito.mock(OpenWeatherWebServiceWrapper::class.java)
//    private var repo = WeatherRepository(InstantAppExecutors(), db, openWeatherWrapper, weatherStackWrapper)
//
//    @Rule
//    @JvmField
//    val instantExecutorRule = InstantTaskExecutorRule()

//    @Test
//    fun loadWeather() {
//        repo.getCurrentWeatherFor("Sydney")
//        verify(db.weatherDao()).getLast()
//    }

//    @Test
//    fun goToNetwork() {
//        val dbData = MutableLiveData<Weather>()
//        `when`(db.weatherDao().getLast()).thenReturn(dbData)
//        val user = TestUtil.createWeatherDto("Sydney")
//        val call = ApiUtil.successCall(user)
//        `when`(weatherStackWrapper.webService.getCurrentWeatherFor("Sydney")).thenReturn(call)
//        val observer = mock<Observer<Resource<Weather>>>()
//
//        repo.getCurrentWeatherFor("Sydney").observeForever(observer)
//        verify(weatherStackWrapper.webService, never()).getCurrentWeatherFor("Sydney")
//        val updatedDbData = MutableLiveData<Weather>()
//        `when`(db.weatherDao().getLast()).thenReturn(updatedDbData)
//        dbData.value = null
//        verify(weatherStackWrapper.webService).getCurrentWeatherFor("Sydney")
//    }
//}