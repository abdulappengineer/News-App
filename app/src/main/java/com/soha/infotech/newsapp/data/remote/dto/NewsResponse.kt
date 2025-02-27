package com.soha.infotech.newsapp.data.remote.dto

import com.soha.infotech.newsapp.domain.model.Article

/**
 * Part 4 : Create a data class for NewsResponse
 */

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
