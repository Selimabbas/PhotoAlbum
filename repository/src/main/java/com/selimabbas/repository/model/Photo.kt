package com.selimabbas.repository.model

import com.selimabbas.remote.model.PhotoEntity

data class Photo(
    val title: String? = null,
    val url: String? = null
)

fun List<PhotoEntity>.toPresentation() =
    map { Photo(it.title, it.url) }