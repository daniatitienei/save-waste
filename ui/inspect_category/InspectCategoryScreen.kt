package com.rozatorii_bulbucasi.savewaste.presentation.ui.inspect_category

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rozatorii_bulbucasi.savewaste.R
import com.rozatorii_bulbucasi.savewaste.data.common.Constants
import com.rozatorii_bulbucasi.savewaste.domain.model.WasteCategory
import com.rozatorii_bulbucasi.savewaste.presentation.theme.Green400
import com.rozatorii_bulbucasi.savewaste.presentation.theme.SaveWasteTheme

@Composable
fun InspectCategoryScreen(
    navController: NavController,
    category: WasteCategory,
) {
    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = category.nameId),
                        style = MaterialTheme.typography.h6
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Rounded.ArrowBack, contentDescription = "back")
                    }
                },
                backgroundColor = Green400,
                elevation = 0.dp
            )
        },
        bottomBar = {
            BottomAppBar(
                contentPadding = PaddingValues(horizontal = 20.dp),
                elevation = 0.dp
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Green400,
                        contentColor = Color.White
                    ),
                    contentPadding = PaddingValues(vertical = 10.dp),
                    shape = CircleShape
                ) {
                    Text(
                        text = stringResource(id = R.string.collection_points_on_map),
                    )
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .background(Green400),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = category.iconId),
                    contentDescription = null,
                    modifier = Modifier.size(150.dp),
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.advantages),
                    style = MaterialTheme.typography.h6
                )

                Spacer(modifier = Modifier.height(10.dp))

                repeat(category.advatanges.size) {
                    Text(text = "${it + 1}. ${stringResource(id = category.advatanges[it])}")
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun InspectCategoryPreview() {
    SaveWasteTheme {
        InspectCategoryScreen(
            navController = rememberNavController(),
            category = Constants.allWasteCategories[0]
        )
    }
}