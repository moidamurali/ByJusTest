package com.murali.byjus.servicedata.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getNews(date: String?, sortBy: String, apiKey: String) =
        apiService.getNews(date, sortBy, apiKey)
}