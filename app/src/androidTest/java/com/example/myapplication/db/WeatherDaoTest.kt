package com.example.myapplication.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import util.TestUtil
import com.example.myapplication.util.getOrAwaitValue
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class WeatherDaoTest : DbTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoad() {
        val weather = TestUtil.createWeather("Sydney")
        db.weatherDao().insertAll(weather)

        val loaded = db.weatherDao().getLast().getOrAwaitValue()
        MatcherAssert.assertThat(loaded.cityName, CoreMatchers.`is`("Sydney"))

        val replacement = TestUtil.createWeather("Tashkent")
        db.weatherDao().insertAll(replacement)

        val loadedReplacement = db.weatherDao().getLast().getOrAwaitValue()
        MatcherAssert.assertThat(loadedReplacement.cityName, CoreMatchers.`is`("Tashkent"))
    }
}