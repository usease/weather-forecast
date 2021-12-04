package com.example.myapplication.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentWeatherBinding
import com.example.myapplication.db.entities.Weather
import com.example.myapplication.network.model.Status
import com.example.myapplication.utils.invisible
import com.example.myapplication.utils.visible
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherFragment : Fragment() {
    private val viewModel: WeatherFragmentViewModel by viewModel()
    lateinit var binding: FragmentWeatherBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentWeatherBinding>(
            inflater,
            R.layout.fragment_weather,
            container,
            false
        )
        observerWeatherInfo()
        viewModel.setQuery("Sydney")

        binding.srl.setOnRefreshListener {
            viewModel.setQuery("Sydney")
            binding.srl.isRefreshing = false
        }

        return binding.root
    }

    private fun observerWeatherInfo() {
        viewModel.weather.observe(viewLifecycleOwner) {
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

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment WeatherFragment.
         */
        @JvmStatic
        fun newInstance() =
            WeatherFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}