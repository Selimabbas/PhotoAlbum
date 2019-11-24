package com.selimabbas.album

import androidx.lifecycle.ViewModel
import com.selimabbas.repository.PhotoRepository

class AlbumViewModel(private val repository: PhotoRepository) : ViewModel() {
    // Load photos only the first time the variable is observed.
    val photos by lazy {
        return@lazy repository.getPhotos()
    }

    /**
     * Reload photos from the repository.
     */
    fun loadPhotos(){
        repository.getPhotos()
    }

    override fun onCleared() {
        repository.cancelOperations()
        super.onCleared()
    }
}
