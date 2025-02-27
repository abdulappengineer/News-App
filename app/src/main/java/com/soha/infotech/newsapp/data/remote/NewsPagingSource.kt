package com.soha.infotech.newsapp.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.soha.infotech.newsapp.domain.model.Article

/**
 * Part 4.4 : Paging Implementation
 *
 * So here we have implemented the paging library
 * The basic function of the paging is to load the data from the api and show it to user
 */

class NewsPagingSource(
    private val newsApi: NewsApi,
    private val source: String,
) : PagingSource<Int, Article>() {

    // Determine when we should stop the paging
    private var totalNewsCount = 0

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        // Gonna make our request to the api and get articles (get the page)
        val page = params.key ?: 1

        return try {
            val newsResponse = newsApi.getNews(page = page, sources = source)
            totalNewsCount += newsResponse.articles.size
            val articles =
                newsResponse.articles.distinctBy { it.title } // to remove any duplicate articles because sometimes the api returns duplicate articatles
            LoadResult.Page(
                data = articles,
                nextKey = if (totalNewsCount == newsResponse.totalResults) null else page + 1 // if it's null the paging will stop loading new result or make new API calls that's why we need the "totalNewsCount"
                ,prevKey = null
            )

        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(
                throwable = e
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        // used to return the page when we call refresh function or when we we load this news for the first time
        return state.anchorPosition?.let { anchorPosition -> // anchorPosition ==> latest access page in the list
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)


        }
    }
}