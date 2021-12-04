package com.rozatorii_bulbucasi.savewaste.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rozatorii_bulbucasi.savewaste.presentation.ui.categories.WasteCategoriesScreen
import com.rozatorii_bulbucasi.savewaste.presentation.ui.home.HomeScreen
import com.rozatorii_bulbucasi.savewaste.presentation.ui.splash.SplashScreen
import com.rozatorii_bulbucasi.savewaste.utils.Screens

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.WasteCategoriesScreenRoute.route) {
        composable(Screens.SplashScreenRoute.route) {
            SplashScreen(navController = navController)
        }

        composable(Screens.HomeScreenRoute.route) {
            HomeScreen(navController = navController)
        }

        composable(Screens.WasteCategoriesScreenRoute.route) {
            WasteCategoriesScreen(navController = navController)
        }
    }
}