package com.ali.celebritiesapp.presentation.screens.details

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
import com.ali.celebritiesapp.data.remote.model.ArtistItem
import com.ali.celebritiesapp.data.remote.model.VenueItem
import com.ali.celebritiesapp.presentation.navigation.CelebritiesScreens
import com.ali.celebritiesapp.presentation.screens.home.HomeScreenViewModel

@ExperimentalMaterial3Api
@Composable
fun DetailsScreen(
    navController: NavHostController,
    type: String,
    entityId: Int,
    viewModel: HomeScreenViewModel,
) {
    var artist = emptyList<ArtistItem>()
    var venue = emptyList<VenueItem>()
    if (type == stringResource(id = R.string.artist)) {
        artist = viewModel.artists.filter { artistItem ->
            artistItem.id == entityId
        }
    } else if (type == stringResource(id = R.string.venue)) {
        venue = viewModel.venues.filter { venueItem ->
            venueItem.id == entityId
        }
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
            if (artist.isNotEmpty()) {
                ArtistDetails(artist = artist.first())
            } else if (venue.isNotEmpty()) {
                VenueDetails(venue = venue.first())
            }
        }
    }

}