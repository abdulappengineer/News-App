package com.soha.infotech.newsapp.domain.usecases.app_entry

import com.soha.infotech.newsapp.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

/**
 * Part 2.3 : Make Use Cases To read app entry
 */

class ReadAppEntry(private val localUserManager: LocalUserManager) {
    // operator ==> so we can call the method with class name
    operator fun invoke(): Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}