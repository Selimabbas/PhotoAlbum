package com.selimabbas.album.di

import com.selimabbas.album.AlbumViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val albumModule = module {
    viewModel { AlbumViewModel(get()) }
}