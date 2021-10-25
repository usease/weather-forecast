package com.example.myapplication.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.db.entities.Weather
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.network.model.Status
import com.example.myapplication.utils.invisible
import com.example.myapplication.utils.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: MainActivityViewModel by viewModel()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        observerWeatherInfo()
        viewModel.setQuery("Sydney")

        binding.srl.setOnRefreshListener {
            viewModel.setQuery("Sydney")
            binding.srl.isRefreshing = false
        }
    }

    private fun observerWeatherInfo() {
        viewModel.weather.observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.groupWeatherInfo.invisible()
                    binding.progressBar.visible()
                }
                Status.ERROR -> {
                    binding.groupWeatherInfo.invisible()
                    binding.progressBar.invisible()
//                    handleError(it.throwable!!)
                }
                Status.SUCCESS -> {
                    binding.groupWeatherInfo.visible()
                    binding.progressBar.invisible()

                    it.data?.let {
                        onWeatherDataReceived(it)
                    }
                }
            }
        }
    }

    private fun onWeatherDataReceived(weather: Weather) {
        weather.apply {
            binding.tvCity.text = cityName
            binding.tvTemperature.text = getString(R.string.x_temperature, temperature)
            binding.tvWind.text = getString(R.string.x_wind_speed, windSpeed)
        }
    }
}