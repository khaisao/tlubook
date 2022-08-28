package com.kito.tlubook.util

import androidx.lifecycle.MutableLiveData

fun <T> mutableLiveDataOf(value: T? = null) = MutableLiveData<T>(value)
