package com.ali.celebritiesapp.domain.model

import com.ali.celebritiesapp.data.remote.model.Artist
import com.ali.celebritiesapp.data.remote.model.Venue

data class VenueItem(
    val id: Int,
    val name: String,
    val sortId: Int,
    val imageUrl: String
)

fun VenueItem.toVenue(): Venue {
    return Venue(id, name, sortId)
}