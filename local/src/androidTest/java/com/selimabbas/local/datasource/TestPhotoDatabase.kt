package com.selimabbas.local.datasource

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.selimabbas.local.PhotoAlbumDatabase
import com.selimabbas.local.dao.PhotoDao
import com.selimabbas.local.model.PhotoLocal
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.inject

class TestPhotoDatabase : KoinTest {

    private val dao: PhotoDao by inject()

    private val photoList = listOf(
        PhotoLocal(
            1, 1, "title", "https://via.placeholder.com/600/cb47e2",
            "https://via.placeholder.com/150/cb47e2"
        ),
        PhotoLocal(
            1, 2, "title", "https://via.placeholder.com/600/4dcdf6",
            "https://via.placeholder.com/150/4dcdf6"
        )
    )

    @Before
    fun setup() {
        startKoin {
            modules(createDatabaseDi(ApplicationProvider.getApplicationContext()))
        }
    }

    private fun createDatabaseDi(context: Context) = module {
        single(named("DATABASE")) {
            Room.inMemoryDatabaseBuilder(context, PhotoAlbumDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        }
        factory { (get(named("DATABASE")) as PhotoAlbumDatabase).getPhotoDao() }
    }

    @Test
    fun saveInDbAndLoadFromDb() {
        var photos = dao.getPhotos()

        assert(photos.isEmpty())

        val listId = dao.savePhotos(photoList)

        assert(listId.isNotEmpty())
        assertEquals(listId.size, photoList.size)

        photos = dao.getPhotos()

        assert(photos.isNotEmpty())
        assertEquals(photos.size, photoList.size)

        assertEquals(photos, photoList)
    }

    @After
    fun stopDi() {
        stopKoin()
    }

}