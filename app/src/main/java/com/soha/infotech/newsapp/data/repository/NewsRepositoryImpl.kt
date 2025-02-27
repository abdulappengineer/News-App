package com.soha.infotech.newsapp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.soha.infotech.newsapp.data.local.NewsDao
import com.soha.infotech.newsapp.data.remote.NewsApi
import com.soha.infotech.newsapp.data.remote.NewsPagingSource
import com.soha.infotech.newsapp.data.remote.SearchPagingSource
import com.soha.infotech.newsapp.domain.model.Article
import com.soha.infotech.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

/**
 * Part 4.5 : Implementing News Repository Implementation
 *
 * Implementing news repository implementation such as
 * Function which returns PagingData
 *
 */

class NewsRepositoryImpl(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao  // Part 5
): NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(10) // tell the pager in each request we expect 10 articles as a response
            , pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    source = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(10) // tell the pager in each request we expect 10 articles as a response
            , pagingSourceFactory = {
                SearchPagingSource( // Part 6
                    searchQuery = searchQuery,
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun upsertArticle(article: Article) {
        newsDao.upsert(article)
    }

    override suspend fun deleteArticle(article: Article) {
        newsDao.delete(article)
    }

    override fun selectArticles(): Flow<List<Article>> {
        return newsDao.getArticles() // to show the lasted in the top
    }

    override suspend fun selectedArticle(url: String): Article? {
        return newsDao.getArticle(url)
    }

    // next: We need to implement use case for this
}