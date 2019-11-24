package com.selimabbas.repository.di

import com.selimabbas.repository.PhotoRepository
import com.selimabbas.repository.PhotoRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory { PhotoRepositoryImpl(get(), get()) as PhotoRepository }
}