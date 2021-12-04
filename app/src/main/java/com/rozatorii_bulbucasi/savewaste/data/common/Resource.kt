package com.rozatorii_bulbucasi.savewaste.data.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Success<T>(data: T?): Resource<T>(data = data)
    class Loading<T>(data: T? = null): Resource<T>(data = data)
    class Error<T>(message: String): Resource<T>(message = message)
}
