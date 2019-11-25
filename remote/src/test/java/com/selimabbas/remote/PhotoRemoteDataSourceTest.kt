package com.selimabbas.remote

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.selimabbas.remote.api.PhotoApi
import com.selimabbas.remote.datasource.PhotoRemoteDataSourceImpl
import com.selimabbas.remote.model.PhotoEntity
import io.reactivex.Single
import org.junit.Test

class PhotoRemoteDataSourceTest {

    private val photoApiMock: PhotoApi = mock()
    private val dataSource =
        PhotoRemoteDataSourceImpl(photoApiMock)

    private val photoList = listOf(
        PhotoEntity(
            1, 1, "title", "https://via.placeholder.com/600/cb47e2",
            "https://via.placeholder.com/150/cb47e2"
        ),
        PhotoEntity(
            1, 2, "title", "https://via.placeholder.com/600/4dcdf6",
            "https://via.placeholder.com/150/4dcdf6"
        )
    )

    @Test
    fun `get photos success`() {
        // Check if the dataSource returns the same as the photo api mock in case of a success.
        whenever(photoApiMock.fetchPhotos()).thenReturn(Single.just(photoList))

        val photoTest = dataSource.getPhotos().test()

        verify(photoApiMock).fetchPhotos()
        photoTest.assertValue(photoList)
    }

    @Test
    fun `get photos failure`() {
        // Check if the dataSource returns the same as the photo api mock in case of a failure.
        val error = Throwable()

        whenever(photoApiMock.fetchPhotos()).thenReturn(Single.error(error))

        val photoTest = dataSource.getPhotos().test()

        verify(photoApiMock).fetchPhotos()
        photoTest.assertError(error)
    }
}