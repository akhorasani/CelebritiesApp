package com.ali.celebritiesapp.data.remote

import com.ali.celebritiesapp.data.remote.model.Artist
import com.ali.celebritiesapp.data.remote.model.ArtistPerformance
import com.ali.celebritiesapp.data.remote.model.Performance
import com.ali.celebritiesapp.data.remote.model.Venue
import com.ali.celebritiesapp.data.remote.model.VenuePerformance
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface CelebritiesAPI {
    @GET("/artists")
    suspend fun getArtists(): List<Artist>

    @GET("/artists/{id}")
    suspend fun getArtistById(@Path("id") id: Int): Artist

    @GET("/artists/{id}/performances")
    suspend fun getArtistPerformances(
        @Path("id") id: Int,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null
    ): List<ArtistPerformance>

    @GET("/venues")
    suspend fun getVenues(): List<Venue>

    @GET("/venues/{id}")
    suspend fun getVenueById(@Path("id") id: Int): Venue

    @GET("/venues/{id}/performances")
    suspend fun getVenuePerformances(
        @Path("id") id: Int,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null
    ): List<VenuePerformance>

    @GET("/performances")
    suspend fun getPerformances(
        @Query("from") from: String? = null,
        @Query("to") to: String? = null
    ): List<Performance>

    @GET("/performances/{id}")
    suspend fun getPerformanceById(@Path("id") id: Int): Performance
}