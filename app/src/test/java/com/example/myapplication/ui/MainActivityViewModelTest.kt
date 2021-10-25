package com.example.myapplication.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.repository.WeatherRepository
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.*
import util.mock

@RunWith(JUnit4::class)
class MainActivityViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repo: WeatherRepository = mock(WeatherRepository::class.java)
    private val viewModel = MainActivityViewModel(repo)

    @Test
    fun testNull() {
        MatcherAssert.assertThat(viewModel.query, CoreMatchers.notNullValue())
    }
}