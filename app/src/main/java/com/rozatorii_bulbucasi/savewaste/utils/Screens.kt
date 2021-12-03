package com.rozatorii_bulbucasi.savewaste.utils

sealed class Screens(val route: String) {
    object SplashScreenRoute: Screens("splash_screen")
    object HomeScreenRoute: Screens("home")
}
