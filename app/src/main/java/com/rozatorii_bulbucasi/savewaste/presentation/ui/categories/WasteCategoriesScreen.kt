package com.rozatorii_bulbucasi.savewaste.presentation.ui.categories

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rozatorii_bulbucasi.savewaste.R
import com.rozatorii_bulbucasi.savewaste.data.common.Constants
import com.rozatorii_bulbucasi.savewaste.data.common.Screens
import com.rozatorii_bulbucasi.savewaste.domain.model.WasteCategory
import com.rozatorii_bulbucasi.savewaste.presentation.theme.SaveWasteTheme
import com.rozatorii_bulbucasi.savewaste.presentation.ui.home.components.Category
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@ExperimentalFoundationApi
@Composable
fun WasteCategoriesScreen(
    navController: NavController,
    moshi: Moshi
) {
    val allWasteCategories = Constants.allWasteCategories

    val jsonAdapter = moshi.adapter(WasteCategory::class.java).lenient()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.categories),
                        style = MaterialTheme.typography.h6
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "back")
                    }
                }
            )
        }
    ) {
        LazyVerticalGrid(
            cells = GridCells.Fixed(2),
            contentPadding = PaddingValues(vertical = 20.dp, horizontal = 20.dp)
        ) {
            items(allWasteCategories) {
                Box(
                    modifier = Modifier.padding(vertical = 15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Category(
                        category = stringResource(id = it.nameId),
                        onClick = {
                            val categoryJson = jsonAdapter.toJson(it)

                            navController.navigate(
                                Screens.InspectWasteCategoryScreenRoute.route.replace(
                                    "{category}",
                                    categoryJson
                                )
                            ) {
                                launchSingleTop = true
                            }
                        },
                        iconId = it.iconId
                    )
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun WasteCategoriesPreview() {
    SaveWasteTheme {
        WasteCategoriesScreen(
            navController = rememberNavController(),
            moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        )
    }
}