package com.rozatorii_bulbucasi.savewaste.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rozatorii_bulbucasi.savewaste.presentation.Navigation
import com.rozatorii_bulbucasi.savewaste.presentation.theme.SaveWasteTheme
import com.rozatorii_bulbucasi.savewaste.presentation.ui.home.HomeScreen
import com.rozatorii_bulbucasi.savewaste.presentation.ui.splash.SplashScreen
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@ExperimentalPermissionsApi
@ExperimentalMaterialApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject lateinit var moshi: Moshi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()

            SaveWasteTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    SideEffect {
                        systemUiController.setSystemBarsColor(
                            color = Color.White,
                            darkIcons = true
                        )
                    }

                    Navigation(moshi = moshi)
                }
            }
        }
    }
}
