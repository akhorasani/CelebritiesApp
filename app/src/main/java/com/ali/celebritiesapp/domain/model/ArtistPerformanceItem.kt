package com.ali.celebritiesapp.domain.model

data class ArtistPerformanceItem(
    val artistId: Int,
    val date: String,
    val id: Int,
    val venue: VenueItem,
)
