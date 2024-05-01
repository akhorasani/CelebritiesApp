package com.ali.celebritiesapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ali.celebritiesapp.presentation.screens.SplashScreen
import com.ali.celebritiesapp.presentation.screens.details.DetailsScreen
import com.ali.celebritiesapp.presentation.screens.details.DetailsScreenViewModel
import com.ali.celebritiesapp.presentation.screens.home.HomeScreen
import com.ali.celebritiesapp.presentation.screens.home.HomeScreenViewModel

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalFoundationApi
@ExperimentalMaterial3Api
@Composable
fun CelebritiesNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = CelebritiesScreens.SplashScreen.name) {
        composable(CelebritiesScreens.SplashScreen.name) {
            SplashScreen(navController = navController)
        }

        composable(CelebritiesScreens.HomeScreen.name) {
            val viewModel = hiltViewModel<HomeScreenViewModel>()
            HomeScreen(viewModel = viewModel) { type, entityId ->
                navController.navigate("${CelebritiesScreens.DetailsScreen.name}/${type}/${entityId}")
            }
        }

        val detailsScreen = CelebritiesScreens.DetailsScreen.name
        composable(
            route = "$detailsScreen/{type}/{entityId}",
            arguments = listOf(
                navArgument("type") {
                    type = NavType.StringType
                },
                navArgument("entityId") {
                    type = NavType.IntType
                })
        ) { backStackEntry ->
            val type = backStackEntry.arguments?.getString("type")
            val entityId = backStackEntry.arguments?.getInt("entityId")
            val viewModel = hiltViewModel<DetailsScreenViewModel>()

            if (entityId != null && type != null) {
                DetailsScreen(navController = navController, type = type, entityId = entityId, viewModel = viewModel)
            }
        }
    }
}