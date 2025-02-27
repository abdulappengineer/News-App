package com.soha.infotech.newsapp.presentation.bookmark

import com.soha.infotech.newsapp.domain.model.Article


data class BookMarkState(
    val articles: List<Article> = emptyList()
)