package com.selimabbas.remote

import com.selimabbas.remote.model.PhotoEntity
import io.reactivex.Observable
import retrofit2.http.GET

interface PhotoApi {

    @GET("photos")
    fun fetchPhotos(): Observable<List<PhotoEntity>>
}