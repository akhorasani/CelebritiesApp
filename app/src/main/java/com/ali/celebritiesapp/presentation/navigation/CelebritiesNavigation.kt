package com.ali.celebritiesapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ali.celebritiesapp.presentation.screens.SplashScreen
import com.ali.celebritiesapp.presentation.screens.details.DetailsScreen
import com.ali.celebritiesapp.presentation.screens.home.HomeScreen

@Composable
fun CelebritiesNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CelebritiesScreens.SplashScreen.name) {
        composable(CelebritiesScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(CelebritiesScreens.HomeScreen.name) {
            HomeScreen()
        }

        composable(CelebritiesScreens.DetailsScreen.name) {
            DetailsScreen()
        }
    }
}