package com.soha.infotech.newsapp.data.remote

import com.soha.infotech.newsapp.data.remote.dto.NewsResponse
import com.soha.infotech.newsapp.utils.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Part 4.2 : News Api Interface
 *
 * Search News will be created later on
 * In get News We have implemented lots of annotations which have @GET and @Query methods
 * And its return type will be NewsResponse
 */

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY

    ): NewsResponse


    @GET("everything")
    suspend fun newsSearch(
        @Query("q") query:String,
        @Query("page") page: Int,
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): NewsResponse
}