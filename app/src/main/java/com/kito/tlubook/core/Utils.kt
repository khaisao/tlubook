package com.kito.tlubook.core

import androidx.lifecycle.MutableLiveData

fun <T> mutableLiveDataOf(value: T? = null) = MutableLiveData<T>(value)
