package com.selimabbas.remote

import com.selimabbas.remote.model.PhotoEntity
import io.reactivex.Observable

interface PhotoDataSource {
    /**
     * Load list of photos.
     */
    fun getPhotos(): Observable<List<PhotoEntity>>
}

class PhotoDataSourceImpl(private val photoApi: PhotoApi) : PhotoDataSource {
    override fun getPhotos() = photoApi.fetchPhotos()
}