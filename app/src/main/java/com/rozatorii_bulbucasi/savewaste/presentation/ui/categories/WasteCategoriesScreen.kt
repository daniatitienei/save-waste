package com.rozatorii_bulbucasi.savewaste.presentation.ui.categories

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rozatorii_bulbucasi.savewaste.R
import com.rozatorii_bulbucasi.savewaste.presentation.theme.SaveWasteTheme

@Composable
fun WasteCategoriesScreen(navController: NavController) {
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
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "back")
                    }
                }
            )
        }
    ) {

    }
}

@Preview
@Composable
private fun WasteCategoriesPreview() {
    SaveWasteTheme {
        WasteCategoriesScreen(navController = rememberNavController())
    }
}