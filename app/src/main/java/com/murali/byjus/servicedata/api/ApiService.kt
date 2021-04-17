package com.murali.byjus.servicedata.api

import com.murali.byjus.servicedata.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //everything?q=tesla&from=2021-04-06&sortBy=publishedAt&apiKey=c87adc3fcc9f4797a2a00d8cbb7fa64a
    @GET("everything?q=tesla")
    fun getNews(
        @Query("from") date: String?,
        @Query("sortBy") userName: String?,
        @Query("apiKey") Password: String?
    ): Call<News?>?

}