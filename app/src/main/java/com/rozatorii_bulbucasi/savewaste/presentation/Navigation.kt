package com.rozatorii_bulbucasi.savewaste.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.rozatorii_bulbucasi.savewaste.presentation.ui.categories.WasteCategoriesScreen
import com.rozatorii_bulbucasi.savewaste.presentation.ui.home.HomeScreen
import com.rozatorii_bulbucasi.savewaste.presentation.ui.maps.MapsScreen
import com.rozatorii_bulbucasi.savewaste.presentation.ui.splash.SplashScreen
import com.rozatorii_bulbucasi.savewaste.data.common.Screens
import com.rozatorii_bulbucasi.savewaste.domain.model.WasteCategory
import com.rozatorii_bulbucasi.savewaste.presentation.ui.inspect_category.InspectCategoryScreen
import com.squareup.moshi.Moshi

@ExperimentalPermissionsApi
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun Navigation(moshi: Moshi) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screens.SplashScreenRoute.route) {
        composable(Screens.SplashScreenRoute.route) {
            SplashScreen(navController = navController)
        }

        composable(Screens.HomeScreenRoute.route) {
            HomeScreen(navController = navController, moshi = moshi)
        }

        composable(Screens.WasteCategoriesScreenRoute.route) {
            WasteCategoriesScreen(navController = navController, moshi = moshi)
        }

        composable(Screens.InspectWasteCategoryScreenRoute.route) { backStackEntry ->
            val categoryJson = backStackEntry.arguments?.getString("category")
            val jsonAdapter = moshi.adapter(WasteCategory::class.java).lenient()
            val categoryObject = jsonAdapter.fromJson(categoryJson)

            InspectCategoryScreen(category = categoryObject!!, navController = navController)
        }

        composable(Screens.MapsScreenRoute.route) {
            MapsScreen(navController = navController)
        }
    }
}
