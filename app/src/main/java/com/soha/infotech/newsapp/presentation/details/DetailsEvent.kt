package com.soha.infotech.newsapp.presentation.details

import com.soha.infotech.newsapp.domain.model.Article


sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article): DetailsEvent()

    data object RemoveSideEffect: DetailsEvent()
}