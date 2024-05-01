package com.ali.celebritiesapp.data.remote.model

data class Performance(
    val artistId: Int,
    val date: String,
    val id: Int,
    val venue: Venue,
)