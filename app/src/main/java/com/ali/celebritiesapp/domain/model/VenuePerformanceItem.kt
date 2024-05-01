package com.ali.celebritiesapp.domain.model

data class VenuePerformanceItem(
    val artist: ArtistItem,
    val date: String,
    val id: Int,
    val venueId: Int
)
