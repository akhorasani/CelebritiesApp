package com.ali.celebritiesapp.presentation.navigation

enum class CelebritiesScreens {
    SplashScreen,
    HomeScreen,
    DetailsScreen;

    companion object {
        fun fromRoute(route: String?): CelebritiesScreens {
            return when (route?.substringBefore("/")) {
                SplashScreen.name -> SplashScreen
                HomeScreen.name -> HomeScreen
                DetailsScreen.name -> DetailsScreen
                null -> HomeScreen
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
        }
    }
}