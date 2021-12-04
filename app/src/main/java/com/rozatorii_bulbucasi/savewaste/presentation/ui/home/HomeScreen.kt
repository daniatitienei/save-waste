package com.rozatorii_bulbucasi.savewaste.presentation.ui.home

import BottomNavigationBar
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.rememberNavController
import com.rozatorii_bulbucasi.savewaste.R
import com.rozatorii_bulbucasi.savewaste.presentation.theme.Green400
import com.rozatorii_bulbucasi.savewaste.presentation.theme.SaveWasteTheme
import com.rozatorii_bulbucasi.savewaste.presentation.ui.home.components.Category
import com.rozatorii_bulbucasi.savewaste.presentation.ui.home.components.OpenableInfoCard
import com.rozatorii_bulbucasi.savewaste.data.common.Screens
import com.rozatorii_bulbucasi.savewaste.presentation.utils.components.TopAppBarWithLogo

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Composable
fun HomeScreen(
    navController: NavController,
) {
    val categoriesRowScrollState = rememberScrollState()

    var wasteRecycled by remember {
        mutableStateOf(0)
    }

    val animationPlayed by remember {
        mutableStateOf(true)
    }

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
        }
    ) {
        LazyColumn(
            contentPadding = PaddingValues(
                start = 20.dp,
                end = 20.dp,
                bottom = 70.dp,
                top = 20.dp
            ),
            verticalArrangement = Arrangement.spacedBy(25.dp),
        ) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(align = Alignment.CenterHorizontally)
                ) {
                    DailyGoalCircularProgressBar(
                        percentage = if (wasteRecycled == 0) 0f else 1f,
                        number = 100,
                        numberOfWasteRecycled = wasteRecycled,
                        animationPlayed = animationPlayed
                    )
                }
            }

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
                        Category(
                            onClick = {
                                /* TODO Redirect to the specific waste category */

                                navController.navigate(Screens.WasteCategoriesScreenRoute.route) {
                                    launchSingleTop = true
                                }
                            },
                            category = "Hârtie",
                        )

                        Spacer(modifier = Modifier.width(15.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun DailyGoalCircularProgressBar(
    percentage: Float = 0f,
    number: Int,
    radius: Dp = 100.dp,
    color: Color = Color.LightGray,
    strokeWidth: Dp = 15.dp,
    animationPlayed: Boolean,
    animDuration: Int = 1000,
    animDelay: Int = 0,
    numberOfWasteRecycled: Int
) {
    val currentPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else -180f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay
        )
    )

    Box(
        modifier = Modifier.size(radius * 2f),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(radius * 2f),
            onDraw = {

                drawArc(
                    startAngle = -180f,
                    sweepAngle = 360f,
                    size = size,
                    useCenter = false,
                    color = Color.LightGray,
                    style = Stroke(
                        width = 15.dp.toPx(),
                        cap = StrokeCap.Round,
                    )
                )

                drawArc(
                    startAngle = -180f,
                    sweepAngle = 360 * currentPercentage.value,
                    size = size,
                    useCenter = false,
                    color = Green400,
                    style = Stroke(
                        width = 15.dp.toPx(),
                        cap = StrokeCap.Round,
                    )
                )
            }
        )

        Column(
            modifier = Modifier.fillMaxWidth(0.7f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "$numberOfWasteRecycled ${stringResource(id = R.string.times)}",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = stringResource(id = R.string.daily_goal),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
private fun Subtitle(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6
    )
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@Preview(showBackground = true)
@Composable
private fun HomePreview() {
    SaveWasteTheme {
        HomeScreen(navController = rememberNavController())
    }
}