package com.example.myapplication.ui

import androidx.lifecycle.*
import com.example.myapplication.repository.WeatherRepository
import com.example.myapplication.db.entities.Weather
import com.example.myapplication.network.model.Resource
import com.example.myapplication.utils.AbsentLiveData

open class MainActivityViewModel(private val repo: WeatherRepository): ViewModel() {
    private val _query = MutableLiveData<String>()
    val query: LiveData<String>
        get() = _query

    val weather: LiveData<Resource<Weather>> = Transformations.switchMap(query) { query ->
        if (query == null)
            AbsentLiveData.create()
        else
            repo.getCurrentWeatherFor(query)
    }

    fun setQuery(query: String) {
        _query.value = query
    }

    fun retry() {
        _query.value?.let {
            _query.value = it
        }
    }
}