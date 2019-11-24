package com.selimabbas.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.selimabbas.local.dao.PhotoDao
import com.selimabbas.local.model.PhotoLocal

@Database(entities = [PhotoLocal::class], version = 1, exportSchema = false)
abstract class PhotoAlbumDatabase : RoomDatabase() {

    abstract fun getPhotoDao(): PhotoDao

    companion object {
        const val DATABASE_NAME = "PhotoAlbum"

        /**
         * Create a unique database for this app. [context] is the application context.
         */
        fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PhotoAlbumDatabase::class.java, "$DATABASE_NAME.db"
            ).build()
    }
}