package com.example.movieapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.detail.DetailsScreen
import com.example.movieapp.screens.home.HomeScreen

@Composable
fun MovieNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController,
        startDestination = MovieScreens.HOME_SCREEN.screen) {

        composable(route = MovieScreens.HOME_SCREEN.screen) {
            HomeScreen(navController = navController)
        }

        composable(route = MovieScreens.DETAIL_SCREEN.screen + "/{movie}",
            arguments = listOf(navArgument(name = "movie") {type = NavType.IntType})) {

            DetailsScreen(navController = navController, it.arguments?.getInt("movie"))
        }
    }
}