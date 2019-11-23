package com.selimabbas.photoalbum.di

import com.selimabbas.remote.di.createRemoteModule


val appComponent = listOf(
    createRemoteModule("http://jsonplaceholder.typicode.com/")
)