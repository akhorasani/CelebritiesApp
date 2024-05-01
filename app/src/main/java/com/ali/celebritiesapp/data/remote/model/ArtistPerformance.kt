package com.ali.celebritiesapp.data.remote.model

data class ArtistPerformance(
    val artistId: Int,
    val date: String,
    val id: Int,
    val venue: Venue,
)