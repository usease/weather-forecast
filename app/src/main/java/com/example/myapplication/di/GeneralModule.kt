package com.example.myapplication.di

import androidx.room.Room
import com.example.myapplication.AppExecutors
import com.example.myapplication.data.constants.Constants
import com.example.myapplication.db.WeatherDatabase
import org.koin.dsl.module

val generalModule = module {
    single { AppExecutors() }

    single {
        Room.databaseBuilder(
            get(),
            WeatherDatabase::class.java, Constants.DATABASE_NAME
        ).build()
    }
}