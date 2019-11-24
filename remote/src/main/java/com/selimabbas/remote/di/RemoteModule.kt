package com.selimabbas.remote.di

import com.selimabbas.remote.PhotoApi
import com.selimabbas.remote.PhotoDataSource
import com.selimabbas.remote.PhotoDataSourceImpl
import com.selimabbas.remote.createNetwork
import org.koin.dsl.module
import retrofit2.Retrofit

fun createRemoteModule(baseUrl: String) = module {
    single { createNetwork(baseUrl) }

    factory { get<Retrofit>().create(PhotoApi::class.java) }

    factory { PhotoDataSourceImpl(get()) as PhotoDataSource }
}