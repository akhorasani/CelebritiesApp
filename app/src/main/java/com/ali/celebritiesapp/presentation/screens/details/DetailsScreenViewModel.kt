package com.ali.celebritiesapp.presentation.screens.details

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ali.celebritiesapp.common.Resource
import com.ali.celebritiesapp.data.remote.model.Artist
import com.ali.celebritiesapp.data.remote.model.ArtistPerformance
import com.ali.celebritiesapp.data.remote.model.Venue
import com.ali.celebritiesapp.data.remote.model.VenuePerformance
import com.ali.celebritiesapp.domain.model.ArtistItem
import com.ali.celebritiesapp.domain.model.ArtistPerformanceItem
import com.ali.celebritiesapp.domain.model.VenueItem
import com.ali.celebritiesapp.domain.model.VenuePerformanceItem
import com.ali.celebritiesapp.domain.repository.CelebritiesRepository
import com.ali.celebritiesapp.utils.Constants
import com.ali.celebritiesapp.utils.calculateCurrentDate
import com.ali.celebritiesapp.utils.calculateFutureDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class DetailsScreenViewModel @Inject constructor(private val repository: CelebritiesRepository) :
    ViewModel() {
    private val _currentDate = MutableLiveData<String>()
    private val _futureDate = MutableLiveData<String>()
    var artistInfo: ArtistItem by mutableStateOf(
        ArtistItem(
            genre = "",
            id = 0,
            name = "",
            imageUrl = ""
        )
    )
    var venueInfo: VenueItem by mutableStateOf(
        VenueItem(
            id = 0,
            name = "",
            sortId = 0,
            imageUrl = ""
        )
    )
    var artistPerformances: List<ArtistPerformanceItem> by mutableStateOf(emptyList())
    var venuePerformances: List<VenuePerformanceItem> by mutableStateOf(emptyList())
    private var isLoading: Boolean by mutableStateOf(true)

    init {
        _currentDate.value = calculateCurrentDate("yyyy-MM-dd")
        _futureDate.value = calculateFutureDate("yyyy-MM-dd")
    }

    fun getArtistById(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                when (val response = repository.getArtistById(id)) {
                    is Resource.Success -> {
                        if (response.data != null) {
                            artistInfo = mapToArtistItem(response.data)
                            isLoading = false
                        } else {
                            Log.d("Network", "showArtist: Failed to load artist")
                        }
                    }

                    is Resource.Error -> {
                        isLoading = false
                        Log.d("Network", "showArtist: Failed to load artist")
                    }

                    else -> {
                        isLoading = false
                    }
                }
            } catch (e: Exception) {
                Log.d("Network", "showArtist: ${e.message.toString()}")
            }
        }
    }

    fun getArtistPerformances(
        id: Int,
        from: String? = null,
        to: String? = null
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                var fromDate = _currentDate.value.toString()
                var toDate = _futureDate.value.toString()
                if (!from.isNullOrEmpty()) {
                    fromDate = from
                }
                if (!to.isNullOrEmpty()) {
                    toDate = to
                }

                when (val response = repository.getArtistPerformances(id, fromDate, toDate)) {
                    is Resource.Success -> {
                        if (!response.data.isNullOrEmpty()) {
                            generateArtistPerformancesImages(
                                response.data.sortedBy { performance ->
                                    performance.date
                                })
                            isLoading = false
                        } else {
                            Log.d("Network", "showPerformances: Failed to load performances")
                        }
                    }

                    is Resource.Error -> {
                        isLoading = false
                        Log.d("Network", "showPerformances: Failed to load performances")
                    }

                    else -> {
                        isLoading = false
                    }
                }
            } catch (e: Exception) {
                Log.d("Network", "showPerformances: ${e.message.toString()}")
            }
        }
    }

    fun getVenueById(id: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                when (val response = repository.getVenueById(id)) {
                    is Resource.Success -> {
                        if (response.data != null) {
                            venueInfo = mapToVenueItem(response.data)
                            isLoading = false
                        } else {
                            Log.d("Network", "showVenue: Failed to load venue")
                        }
                    }


                    is Resource.Error -> {
                        isLoading = false
                        Log.d("Network", "showVenue: Failed to load venue")
                    }


                    else -> {
                        isLoading = false
                    }
                }
            } catch (e: Exception) {
                Log.d("Network", "showVenue: ${e.message.toString()}")
            }
        }
    }

    fun getVenuePerformances(
        id: Int,
        from: String? = null,
        to: String? = null
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            try {
                var fromDate = _currentDate.value.toString()
                var toDate = _futureDate.value.toString()
                if (!from.isNullOrEmpty()) {
                    fromDate = from
                }
                if (!to.isNullOrEmpty()) {
                    toDate = to
                }

                when (val response = repository.getVenuePerformances(id, fromDate, toDate)) {
                    is Resource.Success -> {
                        if (!response.data.isNullOrEmpty()) {
                            generateVenuePerformancesImages(
                                response.data.sortedBy { performance ->
                                    performance.date
                                })
                            isLoading = false
                        } else {
                            Log.d("Network", "showPerformances: Failed to load performances")
                        }
                    }

                    is Resource.Error -> {
                        isLoading = false
                        Log.d("Network", "showPerformances: Failed to load performances")
                    }

                    else -> {
                        isLoading = false
                    }
                }
            } catch (e: Exception) {
                Log.d("Network", "showPerformances: ${e.message.toString()}")
            }
        }
    }

    private fun generateArtistPerformancesImages(performances: List<ArtistPerformance>) {
        this.artistPerformances = performances.map { performance ->
            ArtistPerformanceItem(
                artistId = performance.artistId,
                date = performance.date,
                id = performance.id,
                venue = VenueItem(
                    id = performance.venue.id,
                    name = performance.venue.name,
                    sortId = performance.venue.sortId,
                    imageUrl = Constants.IMAGE_BASE_URL + Constants.VENUES + performance.venue.name + Constants.PNG
                ),
            )
        }
    }

    private fun generateVenuePerformancesImages(performances: List<VenuePerformance>) {
        this.venuePerformances = performances.map { performance ->
            VenuePerformanceItem(
                artist = ArtistItem(
                    genre = performance.artist.genre,
                    id = performance.artist.id,
                    name = performance.artist.name,
                    imageUrl = Constants.IMAGE_BASE_URL + Constants.ARTISTS + performance.artist.name + Constants.PNG
                ),
                date = performance.date,
                id = performance.id,
                venueId = performance.venueId,
            )
        }
    }

    private fun mapToArtistItem(artist: Artist): ArtistItem {
        return ArtistItem(
            genre = artist.genre,
            id = artist.id,
            name = artist.name,
            imageUrl = Constants.IMAGE_BASE_URL + Constants.ARTISTS + artist.name + Constants.PNG
        )
    }

    private fun mapToVenueItem(venue: Venue): VenueItem {
        return VenueItem(
            id = venue.id,
            name = venue.name,
            sortId = venue.sortId,
            imageUrl = Constants.IMAGE_BASE_URL + Constants.VENUES + venue.name + Constants.PNG
        )
    }
}