package com.selimabbas.album.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.selimabbas.album.R
import com.selimabbas.album.utils.Event
import com.selimabbas.repository.PhotoRepository
import com.selimabbas.repository.model.Photo
import com.selimabbas.repository.utils.Resource
import com.selimabbas.repository.utils.Status

class AlbumViewModel(private val repository: PhotoRepository) : ViewModel() {
    private val _photos = MediatorLiveData<List<Photo>>()
    val photos: LiveData<List<Photo>> get() = _photos
    private var photosSource: LiveData<Resource<List<Photo>>> = MutableLiveData()

    private val _errorMessage = MutableLiveData<Event<Int>>()
    val errorMessage get() = _errorMessage

    init {
        loadPhotos()
    }

    /**
     * Reload photos from the repository.
     */
    fun refreshPhotos() {
        loadPhotos()
    }

    /**
     * Set the photo LiveData to update the view.
     */
    private fun loadPhotos() {
        _photos.removeSource(photosSource)
        photosSource = repository.getPhotos()
        _photos.addSource(photosSource) {
            when (it.status) {
                Status.LOADING -> _photos.value = it.data
                Status.SUCCESS -> _photos.value = it.data
                Status.FAILURE -> setErrorMessage(it.error)
            }
        }
    }

    /**
     * Set the errorMessage LiveData to inform the view there was an error.
     */
    private fun setErrorMessage(error: Throwable?) {
        _errorMessage.value = error?.let {
            /*
                        when (it) {
                            is HttpRetryException ->
                        }
            */
            Event(R.string.error_occurred)
        } ?: Event(R.string.error_occurred)

    }

    override fun onCleared() {
        repository.cancelOperations()
        super.onCleared()
    }
}
