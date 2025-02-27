package com.soha.infotech.newsapp.domain.usecases.news


import com.soha.infotech.newsapp.domain.model.Article
import com.soha.infotech.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(): Flow<List<Article>>{
        return newsRepository.selectArticles()
    }
}