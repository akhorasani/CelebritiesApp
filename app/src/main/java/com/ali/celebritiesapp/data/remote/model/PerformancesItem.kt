package com.ali.celebritiesapp.data.remote.model

data class PerformancesItem(
    val artistId: Int,
    val date: String,
    val id: Int,
    val venueId: Int? = null,
    val venueItem: Venue? = null,
)