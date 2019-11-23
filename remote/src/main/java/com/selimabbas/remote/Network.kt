package com.selimabbas.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Create the client used to access remote data using [baseUrl] as API base url.
 */
fun createNetwork(baseUrl: String): Retrofit = retrofitClient(baseUrl, httpClient())

/**
 * Create an http client.
 */
private fun httpClient() = OkHttpClient.Builder().build()

/**
 * Create a retrofit client using [baseUrl] as API base url and [client] as http client
 */
private fun retrofitClient(baseUrl: String, client: OkHttpClient) =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
