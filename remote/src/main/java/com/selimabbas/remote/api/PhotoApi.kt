package com.selimabbas.remote.api

import com.selimabbas.remote.model.PhotoEntity
import io.reactivex.Single
import retrofit2.http.GET

interface PhotoApi {

    @GET("photos")
    fun fetchPhotos(): Single<List<PhotoEntity>>
}