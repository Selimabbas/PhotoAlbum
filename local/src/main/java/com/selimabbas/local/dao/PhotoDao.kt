package com.selimabbas.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.selimabbas.local.model.PhotoLocal

@Dao
interface PhotoDao {

    @Query("SELECT * FROM PhotoLocal ORDER BY id")
    fun getPhotos(): List<PhotoLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun savePhotos(photos: List<PhotoLocal>): List<Long>
}