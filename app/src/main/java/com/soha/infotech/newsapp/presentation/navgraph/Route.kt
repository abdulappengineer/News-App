package com.soha.infotech.newsapp.presentation.navgraph

/**
 * Part 3 : Implementing NavGraph in app
 *
 * Here i have define routes in a sealed class.
 * After that you have to define the Navigation Controller.
 */

sealed class Route(val route: String) {
    data object OnBoardingScreen : Route(route = "onBoardingScreen")

    data object HomeScreen : Route(route = "homeScreen")

    data object SearchScreen : Route(route = "searchScreen")

    data object BookmarkScreen : Route(route = "bookMarkScreen")

    data object DetailsScreen : Route(route = "detailsScreen")

    data object AppStartNavigation : Route(route = "appStartNavigation")

    data object NewsNavigation : Route(route = "newsNavigation")

    data object NewsNavigatorScreen : Route(route = "newsNavigator")
}