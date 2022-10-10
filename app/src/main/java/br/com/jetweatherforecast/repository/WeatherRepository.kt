package br.com.jetweatherforecast.repository

import android.util.Log
import br.com.jetweatherforecast.data.DataOrException
import br.com.jetweatherforecast.model.Weather
import br.com.jetweatherforecast.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(
        cityQuery: String
    ): DataOrException<Weather, Boolean, Exception> {
        val response = try {
            api.getWeather(cityQuery)
        } catch (e: Exception) {
            Log.d("SEIJI", "getWeather : $e")
            return DataOrException(e = e)
        }
        Log.d("SEIJI", "response : $response")
        return DataOrException(data = response)
    }
}