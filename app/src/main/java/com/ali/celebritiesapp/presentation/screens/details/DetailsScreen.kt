package com.ali.celebritiesapp.presentation.screens.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.ali.celebritiesapp.R
import com.ali.celebritiesapp.components.ArtistDetails
import com.ali.celebritiesapp.components.CelebritiesTopAppBar
import com.ali.celebritiesapp.components.VenueDetails
import com.ali.celebritiesapp.domain.model.ArtistItem
import com.ali.celebritiesapp.domain.model.PerformanceItem
import com.ali.celebritiesapp.domain.model.VenueItem
import com.ali.celebritiesapp.presentation.navigation.CelebritiesScreens

@RequiresApi(Build.VERSION_CODES.O)
@ExperimentalMaterial3Api
@Composable
fun DetailsScreen(
    navController: NavHostController,
    type: String,
    entityId: Int,
    viewModel: DetailsScreenViewModel,
) {
    var artist = ArtistItem(genre = "", id = 0, name = "", imageUrl = "")
    var venue = VenueItem(id = 0, name = "", sortId = 0, imageUrl = "")
    var performances = emptyList<PerformanceItem>()

    if (type == stringResource(id = R.string.artist)) {
        viewModel.getArtistById(entityId)
        artist = viewModel.artistInfo
        viewModel.getArtistPerformances(entityId)
        performances = viewModel.performances

    } else if (type == stringResource(id = R.string.venue)) {
        viewModel.getVenueById(entityId)
        venue = viewModel.venueInfo
    }

    Scaffold(
        topBar = {
            CelebritiesTopAppBar(
                title = stringResource(id = R.string.details_screen),
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                onBackArrowClicked = {
                    navController.navigate(CelebritiesScreens.HomeScreen.name)
                }
            )
        }
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (type == stringResource(id = R.string.artist)) {
                ArtistDetails(artist = artist, performances = performances)
            } else if (type == stringResource(id = R.string.venue)) {
                VenueDetails(venue = venue)
            }
        }
    }
}