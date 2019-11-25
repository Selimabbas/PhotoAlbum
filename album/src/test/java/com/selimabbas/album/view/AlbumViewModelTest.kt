package com.selimabbas.album.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.selimabbas.album.R
import com.selimabbas.album.utils.Event
import com.selimabbas.repository.PhotoRepository
import com.selimabbas.repository.model.Photo
import com.selimabbas.repository.utils.Resource
import com.selimabbas.repository.utils.Status
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AlbumViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AlbumViewModel
    private lateinit var repository: PhotoRepository

    private val photoList = listOf(
        Photo("title", "https://via.placeholder.com/600/cb47e2"),
        Photo("title", "https://via.placeholder.com/600/4dcdf6")
    )

    @Before
    fun setup() {
        repository = mockk()
    }

    @Test
    fun `Photo loading success`() {
        val observerError = mockk<Observer<Event<Int>>>(relaxed = true)
        val observerPhotos = mockk<Observer<List<Photo>>>(relaxed = true)

        val photoResult =
            MutableLiveData<Resource<List<Photo>>>(Resource(Status.LOADING, null, null))

        every { repository.getPhotos() } returns photoResult

        viewModel = AlbumViewModel(repository)
        viewModel.photos.observeForever(observerPhotos)
        viewModel.errorMessage.observeForever(observerError)

        photoResult.value = Resource((Status.SUCCESS), photoList, null)

        verify {
            observerPhotos.onChanged(photoList)
        }
    }

    @Test
    fun `Photo loading failure`() {
        val observerError = mockk<Observer<Event<Int>>>(relaxed = true)
        val observerPhotos = mockk<Observer<List<Photo>>>(relaxed = true)

        val photoResult =
            MutableLiveData<Resource<List<Photo>>>(Resource(Status.LOADING, null, null))

        every { repository.getPhotos() } returns photoResult

        viewModel = AlbumViewModel(repository)
        viewModel.photos.observeForever(observerPhotos)
        viewModel.errorMessage.observeForever(observerError)

        photoResult.value = Resource((Status.FAILURE), null, Throwable())

        assertEquals(viewModel.errorMessage.value?.peekContent(), R.string.error_occurred)
    }

}