package com.ali.celebritiesapp.data.repository

import com.ali.celebritiesapp.common.Resource
import com.ali.celebritiesapp.data.remote.CelebritiesAPI
import com.ali.celebritiesapp.data.remote.model.Artist
import com.ali.celebritiesapp.data.remote.model.ArtistPerformance
import com.ali.celebritiesapp.data.remote.model.Performance
import com.ali.celebritiesapp.data.remote.model.Venue
import com.ali.celebritiesapp.data.remote.model.VenuePerformance
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

    override suspend fun getArtistById(id: Int): Resource<Artist> {
        val response = try {
            Resource.Loading(data = true)
            api.getArtistById(id)
        } catch (e: Exception) {
            return Resource.Error(message = "An error occurred ${e.message.toString()}")
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }

    override suspend fun getArtistPerformances(
        id: Int,
        from: String?,
        to: String?
    ): Resource<List<ArtistPerformance>> {
        val response = try {
            Resource.Loading(data = true)
            if (from != null || to != null) {
                api.getArtistPerformances(id, from, to)
            } else {
                api.getArtistPerformances(id)
            }
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

    override suspend fun getVenueById(id: Int): Resource<Venue> {
        val response = try {
            Resource.Loading(data = true)
            api.getVenueById(id)
        } catch (e: Exception) {
            return Resource.Error(message = "An error occurred ${e.message.toString()}")
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }

    override suspend fun getVenuePerformances(
        id: Int,
        from: String?,
        to: String?
    ): Resource<List<VenuePerformance>> {
        val response = try {
            Resource.Loading(data = true)
            if (from != null || to != null) {
                api.getVenuePerformances(id, from, to)
            } else {
                api.getVenuePerformances(id)
            }
        } catch (e: Exception) {
            return Resource.Error(message = "An error occurred ${e.message.toString()}")
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }

    override suspend fun getPerformances(from: String?, to: String?): Resource<List<Performance>> {
        val response = try {
            Resource.Loading(data = true)
            if (from != null || to != null) {
                api.getPerformances(from, to)
            } else {
                api.getPerformances()
            }
        } catch (e: Exception) {
            return Resource.Error(message = "An error occurred ${e.message.toString()}")
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }

    override suspend fun getPerformanceById(id: Int): Resource<Performance> {
        val response = try {
            Resource.Loading(data = true)
            api.getPerformanceById(id)
        } catch (e: Exception) {
            return Resource.Error(message = "An error occurred ${e.message.toString()}")
        }
        Resource.Loading(data = false)
        return Resource.Success(data = response)
    }
}