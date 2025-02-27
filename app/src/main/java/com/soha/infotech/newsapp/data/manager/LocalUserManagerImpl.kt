package com.soha.infotech.newsapp.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.soha.infotech.newsapp.domain.manager.LocalUserManager
import com.soha.infotech.newsapp.utils.Constants
import com.soha.infotech.newsapp.utils.Constants.USER_SETTINGS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Part 2.1 : Here we have created the Local User Manager Implementation
 * where we have implemented the Preferences datastore
 */

// Get instance of data store
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

// Define Preference Keys
private object PreferencesKeys {
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}

class LocalUserManagerImpl(private val context: Context) : LocalUserManager {
    // Save Data to DataStore
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings ->
            settings[PreferencesKeys.APP_ENTRY] = true
        }
    }

    // Read Data from DataStore
    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[PreferencesKeys.APP_ENTRY] ?: false

            // next we need useCase: go to domain layer
        }
    }
}