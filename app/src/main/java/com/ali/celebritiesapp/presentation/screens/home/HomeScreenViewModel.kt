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
import com.ali.celebritiesapp.domain.repository.CelebritiesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: CelebritiesRepository) :
    ViewModel() {

    var artists: List<Artist> by mutableStateOf(emptyList())
    var venues: List<Venue> by mutableStateOf(emptyList())

    var isLoading: Boolean by mutableStateOf(true)

    init {
        getArtists()
    }

    fun getArtists() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                when (val response = repository.getArtists()) {
                    is Resource.Success -> {
                        if (!response.data.isNullOrEmpty()) {
                            artists = response.data
                            isLoading = false
                        } else {
                            Log.i("Network", "showArtists: Failed to load artists")
                        }
                    }

                    is Resource.Error -> {
                        isLoading = false
                        Log.i("Network", "showArtists: Failed to load artists")
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

    fun getVenues() {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                when (val response = repository.getVenues()) {
                    is Resource.Success -> {
                        if (!response.data.isNullOrEmpty()) {
                            venues = response.data
                            isLoading = false
                        } else {
                            Log.i("Network", "showVenues: Failed to load venues")
                        }
                    }

                    is Resource.Error -> {
                        isLoading = false
                        Log.i("Network", "showVenues: Failed to load venues")
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
}