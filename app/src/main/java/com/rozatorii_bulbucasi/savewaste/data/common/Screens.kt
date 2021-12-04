package com.rozatorii_bulbucasi.savewaste.data.common

sealed class Screens(val route: String) {
    object SplashScreenRoute: Screens("splash_screen")
    object HomeScreenRoute: Screens("home")
    object WasteCategoriesScreenRoute: Screens("recycle_categories")
    object MapsScreenRoute: Screens("recycle_categories")
}
