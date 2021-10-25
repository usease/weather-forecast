package com.example.myapplication.api

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.myapplication.network.WeatherStackWebService
import com.example.myapplication.network.model.ApiSuccessResponse
import com.example.myapplication.util.getOrAwaitValue
import com.example.myapplication.utils.LiveDataCallAdapterFactory
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers
import org.hamcrest.core.IsNull
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@RunWith(JUnit4::class)
class WeatherStackWebServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: WeatherStackWebService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(WeatherStackWebService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getWeather() {
        enqueueResponse("sydney.json")
        val sydney = (service.getCurrentWeatherFor("Sydney").getOrAwaitValue() as ApiSuccessResponse).body
        val request = mockWebServer.takeRequest()
        Assert.assertThat(sydney, IsNull.notNullValue())
        Assert.assertThat(sydney.location.name, CoreMatchers.`is`("Sydney"))
        Assert.assertThat(sydney.weatherCurrent.windSpeed, CoreMatchers.`is`(28))
        Assert.assertThat(sydney.weatherCurrent.temperature, CoreMatchers.`is`(25))
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}
