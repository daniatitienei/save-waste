package com.rozatorii_bulbucasi.savewaste.presentation.ui.categories

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rozatorii_bulbucasi.savewaste.R
import com.rozatorii_bulbucasi.savewaste.domain.enum.WasteType
import com.rozatorii_bulbucasi.savewaste.presentation.theme.SaveWasteTheme
import com.rozatorii_bulbucasi.savewaste.presentation.ui.home.components.Category

@ExperimentalFoundationApi
@Composable
fun WasteCategoriesScreen(
    navController: NavController,
    wasteCategoriesViewModel: WasteCategoriesViewModel = hiltViewModel()
) {
    val state = wasteCategoriesViewModel.state.value

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
            items(state.categories) {
                Box(
                    modifier = Modifier.padding(vertical = 15.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Category(
                        category = it.categoryName,
                        onClick = { /*TODO*/ },
                        iconId = getIconIdFromWasteType(it.wasteType)
                    )
                }
            }
        }
    }
}

fun getIconIdFromWasteType(wasteType: WasteType): Int = when(wasteType) {
    WasteType.GLASS -> R.drawable.ic_glass
    WasteType.PAPER -> R.drawable.ic_paper
    WasteType.PLASTIC -> R.drawable.ic_plastic_bottle
    WasteType.OIL -> R.drawable.ic_oil
    WasteType.POLYSTYRENE -> R.drawable.ic_polystyrene
    WasteType.MEDS -> R.drawable.ic_medical_pill
    WasteType.LIGHT_BULBS -> R.drawable.ic_light_bulb
    WasteType.FURNITURE -> R.drawable.ic_furniture
    WasteType.CLOTHES -> R.drawable.ic_clothes
    WasteType.BATTERIES -> R.drawable.ic_battery
}

@ExperimentalFoundationApi
@Preview
@Composable
private fun WasteCategoriesPreview() {
    SaveWasteTheme {
        WasteCategoriesScreen(navController = rememberNavController())
    }
}