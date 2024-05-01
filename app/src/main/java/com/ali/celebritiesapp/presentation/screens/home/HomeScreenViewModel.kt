package com.ali.celebritiesapp.presentation.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.celebritiesapp.common.Resource
import com.ali.celebritiesapp.data.remote.model.Artist
import com.ali.celebritiesapp.data.remote.model.Venue
import com.ali.celebritiesapp.domain.model.ArtistItem
import com.ali.celebritiesapp.domain.model.VenueItem
import com.ali.celebritiesapp.domain.repository.CelebritiesRepository
import com.ali.celebritiesapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: CelebritiesRepository) :
    ViewModel() {

    var artists: List<ArtistItem> by mutableStateOf(emptyList())
    var venues: List<VenueItem> by mutableStateOf(emptyList())

    var isLoading: Boolean by mutableStateOf(true)

    init {
        getArtists()
        getVenues()
    }

    private fun getArtists() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                when (val response = repository.getArtists()) {
                    is Resource.Success -> {
                        if (!response.data.isNullOrEmpty()) {
                            generateArtistImage(
                                response.data.sortedBy { artist ->
                                    artist.name
                                })
                            isLoading = false
                        } else {
                            Log.d("Network", "showArtists: Failed to load artists")
                        }
                    }

                    is Resource.Error -> {
                        isLoading = false
                        Log.d("Network", "showArtists: Failed to load artists")
                    }

                    else -> {
                        isLoading = false
                    }
                }
            } catch (e: Exception) {
                Log.d("Network", "showArtists: ${e.message.toString()}")
            }
        }
    }

    private fun getVenues() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                when (val response = repository.getVenues()) {
                    is Resource.Success -> {
                        if (!response.data.isNullOrEmpty()) {
                            generateVenueImage(
                                response.data.sortedBy { venue ->
                                    venue.sortId
                                })
                            isLoading = false
                        } else {
                            Log.d("Network", "showVenues: Failed to load venues")
                        }
                    }

                    is Resource.Error -> {
                        isLoading = false
                        Log.d("Network", "showVenues: Failed to load venues")
                    }

                    else -> {
                        isLoading = false
                    }
                }
            } catch (e: Exception) {
                Log.d("Network", "showVenues: ${e.message.toString()}")
            }
        }
    }

    private fun generateArtistImage(artists: List<Artist>) {
        this.artists = artists.map { artist ->
            ArtistItem(
                genre = artist.genre,
                id = artist.id,
                name = artist.name,
                imageUrl = Constants.IMAGE_BASE_URL + Constants.ARTISTS + artist.name + Constants.PNG
            )
        }
    }

    private fun generateVenueImage(venues: List<Venue>) {
        this.venues = venues.map { venue ->
            VenueItem(
                id = venue.id,
                name = venue.name,
                sortId = venue.sortId,
                imageUrl = Constants.IMAGE_BASE_URL + Constants.VENUES + venue.name + Constants.PNG
            )
        }
    }

}