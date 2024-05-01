package com.ali.celebritiesapp.data.remote.model

data class VenuePerformance(
    val artist: Artist,
    val date: String,
    val id: Int,
    val venueId: Int
)