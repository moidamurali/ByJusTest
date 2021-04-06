package com.mindorks.retrofit.coroutines.data.repository

import com.murali.byjus.servicedata.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getNews(date : String, sortBy : String, apiKey : String) =
        apiHelper.getNews(date, sortBy, apiKey)
}