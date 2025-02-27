package com.soha.infotech.newsapp.domain.repository

import androidx.paging.PagingData
import com.soha.infotech.newsapp.domain.model.Article
import kotlinx.coroutines.flow.Flow

/**
 * Part 4.3 : Create a NewsRepository interface
 */

interface NewsRepository {

    // Here we will implement the paging and first lets see what is paging
    // Paging basically means that the we will get small, chunks amount of response from the api.
    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    fun selectArticles(): Flow<List<Article>>

    suspend fun selectedArticle(url: String): Article?
}