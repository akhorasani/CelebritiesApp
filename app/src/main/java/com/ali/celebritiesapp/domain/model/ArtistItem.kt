package com.ali.celebritiesapp.domain.model

import com.ali.celebritiesapp.data.remote.model.Artist

data class ArtistItem(
    val genre: String,
    val id: Int,
    val name: String,
    val imageUrl: String
)

fun ArtistItem.toArtist(): Artist {
    return Artist(genre, id, name)
}
