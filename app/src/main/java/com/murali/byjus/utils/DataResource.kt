package com.murali.byjus.utils

import com.mindorks.retrofit.coroutines.utils.ServiceStatus
import com.mindorks.retrofit.coroutines.utils.ServiceStatus.ERROR
import com.mindorks.retrofit.coroutines.utils.ServiceStatus.LOADING
import com.mindorks.retrofit.coroutines.utils.ServiceStatus.SUCCESS


data class DataResource<out T>(val status: ServiceStatus, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): DataResource<T> = DataResource(status = SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): DataResource<T> =
            DataResource(status = ERROR, data = data, message = message)

        fun <T> loading(data: T?): DataResource<T> = DataResource(status = LOADING, data = data, message = null)
    }
}