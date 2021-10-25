package com.example.myapplication.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @ColumnInfo(name = "city_name") val cityName: String,
    @ColumnInfo(name = "temperature") val temperature: Int, // Measured in Celsius
    @ColumnInfo(name = "wind_speed") val windSpeed: Int, // Measured in kilometers per hour
    @ColumnInfo(name = "timestamp") val timestamp: Long,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)