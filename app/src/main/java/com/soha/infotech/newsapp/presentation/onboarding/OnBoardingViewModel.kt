package com.soha.infotech.newsapp.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soha.infotech.newsapp.domain.usecases.app_entry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Part 2.6 : Create the ViewModel to handle OnBoarding screen
 */

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    // Injects an instance of AppEntryUseCases to handle app entry logic.
    private val appEntryUseCases: AppEntryUseCases,
) : ViewModel() {

    // Handles events related to the onboarding process.
    fun onEvent(event: OnBoardingEvent) {
        when (event) {
            // If the event is SaveAppEntry, call saveUserEntry()
            is OnBoardingEvent.SaveAppEntry -> {
                saveUserEntry()
            }
        }
    }

    // Saves the user's app entry status asynchronously.
    private fun saveUserEntry() {
        viewModelScope.launch {
            // Calls the use case to save the app entry.
            appEntryUseCases.saveAppEntry()
        }
    }
}