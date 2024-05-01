package com.ali.celebritiesapp.domain.repository

import com.ali.celebritiesapp.common.Resource
import com.ali.celebritiesapp.data.remote.model.Artist
import com.ali.celebritiesapp.data.remote.model.ArtistPerformance
import com.ali.celebritiesapp.data.remote.model.Performance
import com.ali.celebritiesapp.data.remote.model.Venue
import com.ali.celebritiesapp.data.remote.model.VenuePerformance

interface CelebritiesRepository {
    suspend fun getArtists(): Resource<List<Artist>>

    suspend fun getArtistById(id: Int): Resource<Artist>

    suspend fun getArtistPerformances(
        id: Int,
        from: String? = null,
        to: String? = null
    ): Resource<List<ArtistPerformance>>

    suspend fun getVenues(): Resource<List<Venue>>

    suspend fun getVenueById(id: Int): Resource<Venue>

    suspend fun getVenuePerformances(
        id: Int,
        from: String? = null,
        to: String? = null
    ): Resource<List<VenuePerformance>>

    suspend fun getPerformances(
        from: String? = null,
        to: String? = null
    ): Resource<List<Performance>>

    suspend fun getPerformanceById(id: Int): Resource<Performance>
}