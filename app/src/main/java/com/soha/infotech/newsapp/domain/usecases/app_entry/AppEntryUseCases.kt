package com.soha.infotech.newsapp.domain.usecases.app_entry

/**
 * Part 2.4 :And make another Data Class which contains both
 */

data class AppEntryUseCases(
    val readAppEntry: ReadAppEntry,
    val saveAppEntry: SaveAppEntry
)
