package com.soha.infotech.newsapp.presentation.onboarding

/**
 * Part 1.4.1 : Sealed Classes for OnBoarding Events
 */

sealed class OnBoardingEvent {
    data object SaveAppEntry: OnBoardingEvent()
}