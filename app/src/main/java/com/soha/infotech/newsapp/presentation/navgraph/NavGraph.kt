package com.soha.infotech.newsapp.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.soha.infotech.newsapp.presentation.news_navigator.NewsNavigatorScreen
import com.soha.infotech.newsapp.presentation.onboarding.OnBoardingScreen
import com.soha.infotech.newsapp.presentation.onboarding.OnBoardingViewModel

/**
 * Part 3.1 :Here is the format of the navigation which distributed through graphs
 */

@Composable
fun NavGraph(
    startDestination: String,
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    onEvent = viewModel::onEvent
                    // viewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {

            composable(route = Route.NewsNavigatorScreen.route) {
                NewsNavigatorScreen()
            }

        }
    }
}

