package com.selimabbas.repository.model

import com.selimabbas.remote.model.PhotoEntity

data class Photo(
    val albumId: Int? = null,
    val id: Int? = null,
    val title: String? = null,
    val url: String? = null,
    val thumbnailUrl: String? = null
)

fun List<PhotoEntity>.toPresentation() =
    map { Photo(it.albumId, it.id, it.title, it.url, it.thumbnailUrl) }