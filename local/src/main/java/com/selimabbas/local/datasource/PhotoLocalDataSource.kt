package com.selimabbas.local.datasource

import com.selimabbas.local.dao.PhotoDao
import com.selimabbas.local.model.PhotoLocal

interface PhotoLocalDataSource {
    /**
     * Get all photos from database.
     */
    fun getPhotos(): List<PhotoLocal>

    /**
     * Save list [photos] in the database and get the list of saved ids.
     */
    fun savePhotos(photos: List<PhotoLocal>): List<Long>
}

class PhotoLocalDataSourceImpl(private val dao: PhotoDao) : PhotoLocalDataSource {
    override fun getPhotos() = dao.getPhotos()

    override fun savePhotos(photos: List<PhotoLocal>) = dao.savePhotos(photos)
}