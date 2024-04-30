package com.ali.celebritiesapp.data.repository

import com.ali.celebritiesapp.common.Resource
import com.ali.celebritiesapp.data.remote.CelebritiesAPI
import com.ali.celebritiesapp.data.remote.model.Artist
import com.ali.celebritiesapp.data.remote.model.Venue
import com.ali.celebritiesapp.domain.repository.CelebritiesRepository
import javax.inject.Inject

class CelebritiesRepositoryImpl @Inject constructor(private val api: CelebritiesAPI) :
    CelebritiesRepository {
    override suspend fun getArtists(): Resource<List<Artist>> {
        val response = try {
            Resource.Loading(data = true)
            api.getArtists()
        } catch (e: Exception) {
            return Resource.Error(message = "An error occurred ${e.message.toString()}")
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }

    override suspend fun getVenues(): Resource<List<Venue>> {
        val response = try {
            Resource.Loading(data = true)
            api.getVenues()
        } catch (e: Exception) {
            return Resource.Error(message = "An error occurred ${e.message.toString()}")
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }
}