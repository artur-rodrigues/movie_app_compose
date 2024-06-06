package com.example.movieapp.navigation

enum class MovieScreens(val screen: String) {
    HOME_SCREEN("HomeScreen"),
    DETAIL_SCREEN("DetailScreen");

    companion object {

        fun fromRoute(route: String?): MovieScreens {
            return when(route?.substringBefore("/")) {
                HOME_SCREEN.screen -> HOME_SCREEN
                DETAIL_SCREEN.screen -> DETAIL_SCREEN
                null -> HOME_SCREEN
                else -> throw IllegalArgumentException("Route $route is not recognized")
            }
        }
    }
}