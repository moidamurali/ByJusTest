package com.mindorks.retrofit.coroutines.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.mindorks.retrofit.coroutines.data.repository.MainRepository
import com.murali.byjus.utils.DataResource
import kotlinx.coroutines.Dispatchers


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getUsers(date: String?, sortBy: String, apiKey: String) = liveData(Dispatchers.IO) {
        emit(DataResource.loading(data = null))
        try {
            emit(DataResource.success(data = mainRepository.getNews(date, sortBy, apiKey)))
        } catch (exception: Exception) {
            emit(DataResource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}