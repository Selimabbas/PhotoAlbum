package com.selimabbas.photoalbum.di

import com.selimabbas.album.di.albumModule
import com.selimabbas.remote.di.createRemoteModule
import com.selimabbas.repository.di.repositoryModule


val appComponent = listOf(
    createRemoteModule("http://jsonplaceholder.typicode.com/"),
    albumModule,
    repositoryModule
)