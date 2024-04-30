package com.ali.celebritiesapp.presentation.screens.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun DetailsScreen(navController: NavHostController, entityId: Int) {
    Text(text = entityId.toString())

}