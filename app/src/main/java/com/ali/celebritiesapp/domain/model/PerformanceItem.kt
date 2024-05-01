package com.ali.celebritiesapp.domain.model

data class PerformanceItem(
    val artistId: Int,
    val date: String,
    val id: Int,
    val venue: VenueItem,
)
