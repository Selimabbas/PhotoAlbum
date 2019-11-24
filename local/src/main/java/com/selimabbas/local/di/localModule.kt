package com.selimabbas.local.di

import com.selimabbas.local.PhotoAlbumDatabase
import com.selimabbas.local.datasource.PhotoLocalDataSource
import com.selimabbas.local.datasource.PhotoLocalDataSourceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val localModule = module {
    single(named(PhotoAlbumDatabase.DATABASE_NAME)) {
        PhotoAlbumDatabase.createDatabase(androidContext())
    }

    factory { (get(named(PhotoAlbumDatabase.DATABASE_NAME)) as PhotoAlbumDatabase).getPhotoDao() }

    factory { PhotoLocalDataSourceImpl(get()) as PhotoLocalDataSource }
}