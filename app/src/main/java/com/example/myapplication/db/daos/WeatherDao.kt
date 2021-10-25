package com.example.myapplication.db.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.myapplication.db.entities.Weather

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather ORDER BY timestamp DESC LIMIT 1")
    fun getLast(): LiveData<Weather>

    @Insert
    fun insertAll(vararg list: Weather)
}