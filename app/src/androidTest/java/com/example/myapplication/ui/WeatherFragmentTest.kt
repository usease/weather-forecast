package com.example.myapplication.ui

import androidx.databinding.DataBindingComponent
import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.myapplication.R
import com.example.myapplication.db.entities.Weather
import com.example.myapplication.network.model.Resource
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import util.CountingAppExecutorsRule
import util.TaskExecutorWithIdlingResourceRule

@RunWith(AndroidJUnit4::class)
class WeatherFragmentTest {
    @Rule
    @JvmField
    val executorRule = TaskExecutorWithIdlingResourceRule()

    @Rule
    @JvmField
    val countingAppExecutors = CountingAppExecutorsRule()

    private lateinit var viewModel: WeatherFragmentViewModel
    private val query = MutableLiveData<String>()
    private val weather = MutableLiveData<Resource<Weather>>()

    @Before
    fun init() {
        val scenario = launchFragmentInContainer<WeatherFragment>(themeResId = R.style.Theme_MyApplication)

        viewModel = mock(WeatherFragmentViewModel::class.java)
        `when`(viewModel.query).thenReturn(query)
        `when`(viewModel.weather).thenReturn(weather)
    }

    @Test
    fun rootViewIsDisplayed() {
        onView(withId(R.id.srl)).check(matches(isDisplayed()))
    }

    @Test
    fun showsProgressViewsWhenLoadingWeatherData() {
        query.postValue("Sydney")
        onView(withId(R.id.progress_bar)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_temperature)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tv_wind)).check(matches(not(isDisplayed())))
        onView(withId(R.id.tv_city)).check(matches(not(isDisplayed())))
    }
}