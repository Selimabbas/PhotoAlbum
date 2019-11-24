package com.selimabbas.repository

import androidx.lifecycle.MutableLiveData
import com.selimabbas.local.datasource.PhotoLocalDataSource
import com.selimabbas.remote.datasource.PhotoRemoteDataSource
import com.selimabbas.repository.model.Photo
import com.selimabbas.repository.model.entityToLocal
import com.selimabbas.repository.model.entityToPresentation
import com.selimabbas.repository.model.localToPresentation
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

class PhotoRepositoryImpl(
    private val remoteDataSource: PhotoRemoteDataSource,
    private val localDataSource: PhotoLocalDataSource
) : PhotoRepository {
    private val compositeDisposable = CompositeDisposable()

    override fun getPhotos(): MutableLiveData<Resource<List<Photo>>> {
        val liveData = MutableLiveData<Resource<List<Photo>>>(
            Resource(Status.LOADING, null, null)
        )
        compositeDisposable.clear()
        compositeDisposable.add(
            remoteDataSource.getPhotos()
                .subscribeOn(Schedulers.io())
                .subscribe({
                    liveData.postValue(
                        Resource(Status.SUCCESS, it.entityToPresentation(), null)
                    )
                    localDataSource.savePhotos(it.entityToLocal())
                },
                    {
                        // When the call to api fails we try first to load the photos from the database.
                        val dbResult = localDataSource.getPhotos()
                        val data =
                            if (dbResult.isNotEmpty()) dbResult.localToPresentation() else null
                        liveData.postValue(Resource(Status.FAILURE, data, it))
                    })
        )
        return liveData
    }

    override fun cancelOperations() {
        compositeDisposable.dispose()
    }

}
