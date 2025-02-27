package com.soha.infotech.newsapp.domain.usecases.app_entry

import com.soha.infotech.newsapp.domain.manager.LocalUserManager

/**
 * Part 2.2 : Make Use Cases To save app entry
 */

class SaveAppEntry(private val localUserManager: LocalUserManager) {
    // operator ==> so we can call the method with class name
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}