package com.soha.infotech.newsapp.domain.usecases.news

import com.soha.infotech.newsapp.domain.model.Article
import com.soha.infotech.newsapp.domain.repository.NewsRepository


class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}