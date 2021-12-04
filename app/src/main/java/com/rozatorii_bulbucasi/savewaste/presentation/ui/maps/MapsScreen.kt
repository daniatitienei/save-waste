package com.rozatorii_bulbucasi.savewaste.presentation.ui.maps

import BottomNavigationBar
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.rozatorii_bulbucasi.savewaste.data.common.Screens
import com.rozatorii_bulbucasi.savewaste.presentation.utils.components.TopAppBarWithLogo

@Composable
fun MapsScreen(
    navController: NavController
) {
    Scaffold(
        topBar = { TopAppBarWithLogo() },
        bottomBar = {
            BottomNavigationBar(
                onHomeClick = {
                    navController.navigate(Screens.HomeScreenRoute.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true

                        restoreState = true
                    }
                },
                onMapsClick = {
                    navController.navigate(Screens.MapsScreenRoute.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }

                        launchSingleTop = true

                        restoreState = true
                    }
                },
                navController = navController
            )
        },
    ) {}
}

