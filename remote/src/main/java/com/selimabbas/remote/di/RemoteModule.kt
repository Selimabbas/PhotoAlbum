package com.selimabbas.remote.di

import com.selimabbas.remote.createNetwork
import org.koin.dsl.module

fun createRemoteModule(baseUrl: String) = module {
    createNetwork(baseUrl)
}