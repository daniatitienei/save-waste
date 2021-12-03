package com.rozatorii_bulbucasi.savewaste.presentation.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rozatorii_bulbucasi.savewaste.R
import com.rozatorii_bulbucasi.savewaste.presentation.theme.Green400
import com.rozatorii_bulbucasi.savewaste.presentation.theme.SaveWasteTheme
import com.rozatorii_bulbucasi.savewaste.presentation.ui.home.components.Category
import com.rozatorii_bulbucasi.savewaste.presentation.ui.home.components.OpenableInfoCard
import java.util.*

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen() {

    val categoriesRowScrollState = rememberScrollState()

    Scaffold(
        topBar = { TopAppBarWithLogo() },
        bottomBar = {
            BottomNavigationBar(
                onHomeClick = { /*TODO*/ },
                onMapsClick = { /*TODO*/ }
            )
        },
    ) {
        LazyColumn(
            contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 70.dp, top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp),
        ) {

            item {
                Text(
                    text = buildAnnotatedString {
                        append(stringResource(id = R.string.total_waste_recycled_by_our_users))
                        withStyle(style = SpanStyle(color = Green400)) {
                            append(text = " 120 ${stringResource(id = R.string.waste)}")
                        }
                    },
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center
                )
            }

            item {
                Subtitle(text = stringResource(id = R.string.faq))

                Spacer(modifier = Modifier.height(10.dp))

                OpenableInfoCard(
                    question = stringResource(id = R.string.why_you_should_recycle_question),
                    answer = stringResource(id = R.string.why_you_should_recycle_answer)
                )

                Spacer(modifier = Modifier.height(20.dp))

                OpenableInfoCard(
                    question = stringResource(id = R.string.how_to_separate_the_items_for_recycling_question),
                    answer = stringResource(id = R.string.how_to_separate_the_items_for_recycling_answer)
                )
            }

            item {
                Subtitle(text = stringResource(id = R.string.categories))

                Spacer(modifier = Modifier.height(15.dp))

                Row(
                    modifier = Modifier.horizontalScroll(categoriesRowScrollState)
                ) {
                    repeat(5) {
                        Category()
                        Spacer(modifier = Modifier.width(15.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(
    onHomeClick: () -> Unit,
    onMapsClick: () -> Unit
) {
    BottomNavigation {
        BottomNavigationItem(
            selected = true,
            onClick = onHomeClick,
            icon = {
                Icon(
                    Icons.Rounded.Home,
                    contentDescription = stringResource(id = R.string.home)
                )
            },
            selectedContentColor = Green400,
            unselectedContentColor = Color.LightGray,
            alwaysShowLabel = true,
            label = { Text(text = stringResource(id = R.string.home)) }
        )

        BottomNavigationItem(
            selected = false,
            onClick = onMapsClick,
            icon = {
                Icon(
                    Icons.Rounded.LocationOn,
                    contentDescription = stringResource(id = R.string.maps)
                )
            },
            selectedContentColor = Green400,
            unselectedContentColor = Color.LightGray,
        )
    }
}

@Composable
private fun Subtitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6
    )
}

@Composable
private fun TopAppBarWithLogo() {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.recycling_logo),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = stringResource(id = R.string.app_name)
                        .uppercase(Locale.getDefault()),
                    fontWeight = FontWeight.Bold
                )
            }
        }
    )
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
private fun HomeScreenLightPreview() {
    SaveWasteTheme {
        HomeScreen()
    }
}