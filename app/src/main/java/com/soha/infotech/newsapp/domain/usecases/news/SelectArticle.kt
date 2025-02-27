package com.soha.infotech.newsapp.domain.usecases.news

import com.soha.infotech.newsapp.domain.model.Article
import com.soha.infotech.newsapp.domain.repository.NewsRepository


class SelectArticle(
    private val newsRepository: NewsRepository
) {
    suspend operator fun invoke(url: String): Article? {
        return newsRepository.selectedArticle(url = url)
    }
}