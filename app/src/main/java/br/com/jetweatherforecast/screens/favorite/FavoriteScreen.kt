package br.com.jetweatherforecast.screens.favorite

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun FavoriteScreen(navController: NavController, favoriteViewModel: FavoriteViewModel) {
    Text(text = "Favorite")
}