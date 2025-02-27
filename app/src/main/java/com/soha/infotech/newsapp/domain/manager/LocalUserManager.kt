package com.soha.infotech.newsapp.domain.manager

import kotlinx.coroutines.flow.Flow

/**
 * Part 2 : We are using Preferences Datastore
 * because we have small amount of data to be visible
 */

interface LocalUserManager {
    // save the app entry
    suspend fun saveAppEntry()

    // read the app entry
    fun readAppEntry(): Flow<Boolean>
}