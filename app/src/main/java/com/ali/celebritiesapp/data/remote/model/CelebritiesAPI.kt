package com.ali.celebritiesapp.data.remote.model

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface CelebritiesAPI {
    @GET("/artists")
    suspend fun getArtists(): Artists

    @GET("/artists/{id}")
    suspend fun getArtistById(@Path("id") id: Int): ArtistsItem

    @GET("/artists/{id}/performances")
    suspend fun getArtistPerformances(
        @Path("id") id: Int,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null
    ): Performances

    @GET("/venues")
    suspend fun getVenues(): Venues

    @GET("/venues/{id}")
    suspend fun getVenueById(@Path("id") id: Int): VenuesItem

    @GET("/venues/{id}/performances")
    suspend fun getVenuePerformances(
        @Path("id") id: Int,
        @Query("from") from: String? = null,
        @Query("to") to: String? = null
    ): Performances

    @GET("/performances")
    suspend fun getPerformances(
        @Query("from") from: String? = null,
        @Query("to") to: String? = null
    ): Performances

    @GET("/performances/{id}")
    suspend fun getPerformanceById(@Path("id") id: Int): PerformancesItem
}