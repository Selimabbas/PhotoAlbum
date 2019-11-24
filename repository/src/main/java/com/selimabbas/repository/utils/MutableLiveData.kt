package com.selimabbas.repository.utils

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData(default: T) = MutableLiveData<T>().apply { value = default }