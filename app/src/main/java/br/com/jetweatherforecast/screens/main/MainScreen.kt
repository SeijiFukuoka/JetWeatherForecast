package br.com.jetweatherforecast.screens.main

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import br.com.jetweatherforecast.data.DataOrException
import br.com.jetweatherforecast.model.Weather

@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    ShowData(mainViewModel)
}

@Composable
fun ShowData(mainViewModel: MainViewModel) {
    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(city = "Seattle")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        Text(text = "MainScreen = ${weatherData.data.toString()}")
    } else {
        weatherData.e
    }

}