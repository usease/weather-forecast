package com.example.myapplication.di

import com.example.myapplication.ui.WeatherFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {
    viewModel { WeatherFragmentViewModel(get()) }
}