package com.selimabbas.remote.di

import com.selimabbas.remote.api.PhotoApi
import com.selimabbas.remote.createNetwork
import com.selimabbas.remote.datasource.PhotoRemoteDataSource
import com.selimabbas.remote.datasource.PhotoRemoteDataSourceImpl
import org.koin.dsl.module
import retrofit2.Retrofit

fun createRemoteModule(baseUrl: String) = module {
    single { createNetwork(baseUrl) }

    factory { get<Retrofit>().create(PhotoApi::class.java) }

    factory { PhotoRemoteDataSourceImpl(get()) as PhotoRemoteDataSource }
}