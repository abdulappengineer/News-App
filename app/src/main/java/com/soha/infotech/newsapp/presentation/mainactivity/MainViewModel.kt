package com.soha.infotech.newsapp.presentation.mainactivity

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.soha.infotech.newsapp.domain.usecases.app_entry.AppEntryUseCases
import com.soha.infotech.newsapp.presentation.navgraph.Route
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

/**
 * Part 3.2 : Implementing Preferences Datastore + Dependency Injection + Onboarding ViewModel
 */

@HiltViewModel
class MainViewModel @Inject constructor(appEntryUseCases: AppEntryUseCases) : ViewModel() {

    // described Splash Condition as true.
    var splashCondition by mutableStateOf(true)
        private set
    // `private set` restricts modification of `splashCondition` to within this class,
    // while still allowing read access from outside.

    var startDestination by mutableStateOf(Route.AppStartNavigation.route)
        private set

    init {
        appEntryUseCases.readAppEntry().onEach { shouldStartFromHomeScreen ->
            if (shouldStartFromHomeScreen) {
                // If it will become true then we will navigate to Home Screen.
                startDestination = Route.NewsNavigation.route
            } else {
                // if false then we will navigate to OnBoarding Screen.
                startDestination = Route.AppStartNavigation.route
            }
            delay(300)
            splashCondition = false
        }.launchIn(viewModelScope)
    }

}