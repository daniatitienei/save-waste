package com.rozatorii_bulbucasi.savewaste.presentation.ui.splash

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rozatorii_bulbucasi.savewaste.R
import com.rozatorii_bulbucasi.savewaste.presentation.theme.SaveWasteTheme
import com.rozatorii_bulbucasi.savewaste.utils.Screens
import kotlinx.coroutines.delay
import java.util.*

@Composable
fun SplashScreen(
    navController: NavController
) {
    val scaleAnimatable = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        scaleAnimatable.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }
            )
        )
        delay(500L)

        navController.navigate(Screens.HomeScreenRoute.route) {
            launchSingleTop = true

            popUpTo(Screens.SplashScreenRoute.route) {
                inclusive = true
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.Center)
    ) {
        AppLogo(scaleAnimatable = scaleAnimatable)
    }
}

@Composable
private fun AppLogo(scaleAnimatable: Animatable<Float, AnimationVector1D>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .scale(scaleAnimatable.value),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.recycling_logo),
            contentDescription = null,
            modifier = Modifier.size(35.dp)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = stringResource(id = R.string.app_name)
                .uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.h5
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashScreenLightPreview() {
    SaveWasteTheme {
        SplashScreen(navController = rememberNavController())
    }
}