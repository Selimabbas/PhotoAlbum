package com.selimabbas.repository.model

import com.selimabbas.local.model.PhotoLocal
import com.selimabbas.remote.model.PhotoEntity

data class Photo(
    val title: String? = null,
    val url: String? = null
)

fun List<PhotoEntity>.entityToPresentation() = map { Photo(it.title, it.url) }

fun List<PhotoLocal>.localToPresentation() = map { Photo(it.title, it.url) }

fun List<PhotoEntity>.entityToLocal() = map {
    PhotoLocal(it.albumId, it.id, it.title, it.url, it.thumbnailUrl)
}