package com.h5190063_gorkemaydogdu_but.util

sealed class Resource<T>(
        val data: T?, // data değişkenine ne gelirse
        val throwable: Throwable?, // hata fırlatmak için kullanılır
        val status: ResourceStatus // durum tutulacak
) {
    class Loading<T> : Resource<T>(null, null, ResourceStatus.LOADING)
    class Success<T>(data: T?) : Resource<T>(data, null, ResourceStatus.SUCCESS)
    class Error<T>(exception: Exception) : Resource<T>(null, exception, ResourceStatus.ERROR)
}

enum class ResourceStatus {
    LOADING,
    SUCCESS,
    ERROR
}