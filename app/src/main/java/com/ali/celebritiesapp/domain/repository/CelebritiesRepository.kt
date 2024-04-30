package com.ali.celebritiesapp.domain.repository

import com.ali.celebritiesapp.common.Resource
import com.ali.celebritiesapp.data.remote.model.Artist
import com.ali.celebritiesapp.data.remote.model.Venue


interface CelebritiesRepository {
    suspend fun getArtists(): Resource<List<Artist>>
    suspend fun getVenues(): Resource<List<Venue>>
}