package com.selimabbas.remote.datasource

import com.selimabbas.remote.api.PhotoApi
import com.selimabbas.remote.model.PhotoEntity
import io.reactivex.Single

interface PhotoRemoteDataSource {
    /**
     * Load list of photos.
     */
    fun getPhotos(): Single<List<PhotoEntity>>
}

class PhotoRemoteDataSourceImpl(private val photoApi: PhotoApi) : PhotoRemoteDataSource {
    override fun getPhotos() = photoApi.fetchPhotos()
}