package util

import com.example.myapplication.db.entities.Weather
import com.example.myapplication.network.model.weather_stack.Location
import com.example.myapplication.network.model.weather_stack.Request
import com.example.myapplication.network.model.weather_stack.WeatherCurrent
import com.example.myapplication.network.model.weather_stack.WeatherStackDto

object TestUtil {
    fun createWeatherDto(query: String) = WeatherStackDto(
        WeatherCurrent(1,1,1, "yes", "", 1, 1, 1, 1, 1, 1, listOf(), listOf(), 1, "", 1),
        Location("", "", "", 1, "", "", "", "", ""),
        Request("", query, "", "")
    )

    fun createWeather(query: String): Weather {
        return Weather(query, 1, 1, System.currentTimeMillis())
    }
}