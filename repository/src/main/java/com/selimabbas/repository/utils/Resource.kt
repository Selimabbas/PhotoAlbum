package com.selimabbas.repository.utils

data class Resource<out T>(val status: Status, val data: T?, val error: Throwable?)

enum class Status {
    LOADING,
    SUCCESS,
    FAILURE
}