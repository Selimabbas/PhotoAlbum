package com.selimabbas.repository

import androidx.lifecycle.MutableLiveData
import com.selimabbas.remote.PhotoDataSource
import com.selimabbas.repository.model.Photo
import com.selimabbas.repository.model.toPresentation
import com.selimabbas.repository.utils.Resource
import com.selimabbas.repository.utils.Status
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

interface PhotoRepository {
    /**
     * Load list of photos as a LiveData.
     */
    fun getPhotos(): MutableLiveData<Resource<List<Photo>>>

    /**
     * Cancel current rx operations.
     */
    fun cancelOperations()
}

class PhotoRepositoryImpl(private val dataSource: PhotoDataSource) : PhotoRepository {
    private val compositeDisposable = CompositeDisposable()

    override fun getPhotos(): MutableLiveData<Resource<List<Photo>>> {
        val liveData = MutableLiveData<Resource<List<Photo>>>(
            Resource(Status.LOADING, null, null)
        )
        compositeDisposable.clear()
        compositeDisposable.add(dataSource.getPhotos()
            .subscribeOn(Schedulers.io())
            .map { it.toPresentation() }
            .subscribe({ liveData.postValue(Resource(Status.SUCCESS, it, null)) },
                {
                    //                    val dbResult = loadFromDb()
                    liveData.postValue(Resource(Status.FAILURE, null, it))
                })
        )
        return liveData
    }

    override fun cancelOperations() {
        compositeDisposable.dispose()
    }

}
