package br.com.jetweatherforecast.screens.main

import androidx.lifecycle.ViewModel
import br.com.jetweatherforecast.data.DataOrException
import br.com.jetweatherforecast.model.Weather
import br.com.jetweatherforecast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    suspend fun getWeatherData(city: String, units: String): DataOrException<Weather, Boolean, Exception> {
        return repository.getWeather(cityQuery = city, units = units)
    }
}