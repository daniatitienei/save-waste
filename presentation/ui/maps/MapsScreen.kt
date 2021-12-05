package com.rozatorii_bulbucasi.savewaste.presentation.ui.maps

import BottomNavigationBar
import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.IntentSender
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.Task
import com.google.android.libraries.maps.CameraUpdateFactory
import com.google.android.libraries.maps.MapView
import com.google.android.libraries.maps.model.BitmapDescriptorFactory
import com.google.android.libraries.maps.model.LatLng
import com.google.android.libraries.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import com.rozatorii_bulbucasi.savewaste.R
import com.rozatorii_bulbucasi.savewaste.data.common.Constants
import com.rozatorii_bulbucasi.savewaste.data.common.Screens
import com.rozatorii_bulbucasi.savewaste.domain.model.WasteCategory
import com.rozatorii_bulbucasi.savewaste.presentation.theme.*
import com.rozatorii_bulbucasi.savewaste.presentation.ui.maps.components.WasteCategoryToggle
import com.rozatorii_bulbucasi.savewaste.presentation.utils.CounterViewModel
import com.rozatorii_bulbucasi.savewaste.presentation.utils.components.TopAppBarWithLogo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@SuppressLint("MissingPermission")
@ExperimentalPermissionsApi
@Composable
fun MapsScreen(
    navController: NavController,
    mapsViewModel: MapsViewModel = hiltViewModel(),
    counterViewModel: CounterViewModel = hiltViewModel()
) {
    val mapView = rememberMapViewWithLifecycle()

    val state = mapsViewModel.state.value

    var selectedCategory by remember { mutableStateOf(R.string.all) }
    val scrollState = rememberScrollState()

    LaunchedEffect(key1 = true) {
        mapsViewModel.getRecyclePoints()
    }

    val locationPermission =
        rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    val geocoder = Geocoder(context, Locale.getDefault())

    var isLocationEnabled by remember {
        mutableStateOf(false)
    }

    // Opens up a google location alert dialog
    val settingResultRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { activityResult ->
            if (activityResult.resultCode == RESULT_OK) {
                Log.d("appDebug", "Accepted")
                isLocationEnabled = true
            } else
                Log.d("appDebug", "Denied")
        }
    )

    LaunchedEffect(key1 = locationPermission.hasPermission) {
        if (!locationPermission.hasPermission) {
            locationPermission.launchPermissionRequest()
        }

        if (locationPermission.hasPermission) {
            checkLocationSetting(
                context = context,
                onDisabled = { intentSenderRequest ->
                    settingResultRequest.launch(intentSenderRequest)
                },
                onEnabled = {
                    Log.d("appDebug", "Accepted")
                    isLocationEnabled = true
                }
            )
        }
    }

    var city by remember {
        mutableStateOf(LatLng(45.7498856, 21.2052476))
    }

    if (state.isLoading)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        ) {
            CircularProgressIndicator(color = Green400)
        }
    else
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
            floatingActionButton = {
                FloatingActionButton(onClick = {
                    scope.launch {
                        counterViewModel.increaseCounter()
                    }
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_recycle),
                        contentDescription = null,
                        modifier = Modifier.size(35.dp)
                    )
                }
            },
            isFloatingActionButtonDocked = true,
            floatingActionButtonPosition = FabPosition.Center
        ) { innerPadding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                AndroidView(
                    { mapView }, modifier = Modifier.weight(0.6f)
                ) { mapView ->
                    CoroutineScope(Dispatchers.Main).launch {
                        val map = mapView.awaitMap()

                        map.clear()

                        map.uiSettings.isZoomControlsEnabled = true
                        map.uiSettings.isTiltGesturesEnabled = false
                        map.uiSettings.isIndoorLevelPickerEnabled = false
                        map.uiSettings.isMyLocationButtonEnabled = isLocationEnabled
                        map.isMyLocationEnabled = isLocationEnabled

                        checkLocationSetting(
                            context = context,
                            onDisabled = { intentSenderRequest ->
                                settingResultRequest.launch(intentSenderRequest)
                            },
                            onEnabled = {
                                fusedLocationClient.lastLocation
                                    .addOnSuccessListener { location: Location? ->
                                        city = location?.latitude?.let { LatLng(it, location.longitude) }!!
                                    }
                                isLocationEnabled = true
                            }
                        )

                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(city, 14f))

                        repeat(state.data.size) {
                            val currentRecyclePoint =
                                LatLng(state.data[it].latitude, state.data[it].longitude)

                            val markerOptions = MarkerOptions().position(currentRecyclePoint)
                                .icon(
                                    BitmapDescriptorFactory.defaultMarker(
                                        when (state.data[it].collectionType) {
                                            "sticlă" -> BitmapDescriptorFactory.HUE_GREEN
                                            "baterii" -> BitmapDescriptorFactory.HUE_ORANGE
                                            "haine" -> BitmapDescriptorFactory.HUE_RED
                                            "deșeuri voluminoase" -> BitmapDescriptorFactory.HUE_VIOLET
                                            "ulei utilizat" -> BitmapDescriptorFactory.HUE_YELLOW
                                            "pet" -> BitmapDescriptorFactory.HUE_AZURE
                                            "hârtie" -> BitmapDescriptorFactory.HUE_BLUE
                                            "polistiren" -> BitmapDescriptorFactory.HUE_ROSE
                                            "medicamente" -> 90f
                                            "becuri" -> BitmapDescriptorFactory.HUE_CYAN
                                            else -> BitmapDescriptorFactory.HUE_CYAN
                                        }
                                    )
                                )
                            map.addMarker(markerOptions)
                        }
                    }
                }
                Column(
                    modifier = Modifier
                        .weight(0.4f)
                        .verticalScroll(scrollState)
                ) {
                    WasteCategoryToggle(
                        wasteCategory = WasteCategory(R.string.all),
                        selectedCategory = stringResource(id = selectedCategory),
                        onClick = { category ->
                            selectedCategory = category
                            mapsViewModel.getRecyclePoints()
                        },
                        color = Green200
                    )

                    repeat(Constants.allWasteCategories.size) {
                        WasteCategoryToggle(
                            wasteCategory = Constants.allWasteCategories[it],
                            selectedCategory = stringResource(id = selectedCategory),
                            onClick = { category ->
                                selectedCategory = category
                                mapsViewModel.getACategoryOfRecyclePoints(
                                    getQueryForHttpRequest(selectedCategory)
                                )
                            },
                            color = getColorByCategory(Constants.allWasteCategories[it].nameId)
                        )
                    }
                }
            }
        }
}

@Composable
private fun rememberMapViewWithLifecycle(): MapView {
    val context = LocalContext.current
    val mapView = remember {
        MapView(context).apply {
            id = com.google.maps.android.ktx.R.id.map_frame
        }
    }

    val lifecycleObserver = rememberMapLifecycleObserver(mapView = mapView)
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(key1 = lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
        }
    }

    return mapView
}

@Composable
private fun rememberMapLifecycleObserver(mapView: MapView): LifecycleObserver = remember(mapView) {
    LifecycleEventObserver { _, event ->
        when (event) {
            Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
            Lifecycle.Event.ON_START -> mapView.onStart()
            Lifecycle.Event.ON_RESUME -> mapView.onResume()
            Lifecycle.Event.ON_PAUSE -> mapView.onPause()
            Lifecycle.Event.ON_STOP -> mapView.onStop()
            Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
            else -> throw IllegalStateException()
        }
    }
}

private fun getQueryForHttpRequest(category: Int): String = when (category) {
    R.string.glass -> "sticlă"
    R.string.batteries -> "baterii"
    R.string.clothes -> "haine"
    R.string.furniture -> "deșeuri voluminoase"
    R.string.light_bulbs -> "becuri"
    R.string.meds -> "medicamente"
    R.string.oil -> "ulei utilizat"
    R.string.plastic -> "pet"
    else -> "polistiren"
}

private fun getColorByCategory(category: Int): Color = when (category) {
    R.string.glass -> Color.Green
    R.string.batteries -> Orange
    R.string.clothes -> Color.Red
    R.string.furniture -> Purple600
    R.string.light_bulbs -> Color.Cyan
    R.string.meds -> Green500
    R.string.oil -> Color.Yellow
    R.string.plastic -> Color.Blue
    else -> Rose
}

private fun checkLocationSetting(
    context: Context,
    onDisabled: (IntentSenderRequest) -> Unit,
    onEnabled: () -> Unit
) {
    val locationRequest = LocationRequest.create().apply {
        interval = 1000
        fastestInterval = 1000
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
    }

    val client: SettingsClient = LocationServices.getSettingsClient(context)
    val builder: LocationSettingsRequest.Builder = LocationSettingsRequest
        .Builder()
        .addLocationRequest(locationRequest)

    val gpsSettingTask: Task<LocationSettingsResponse> =
        client.checkLocationSettings(builder.build())

    gpsSettingTask.addOnSuccessListener { onEnabled() }

    gpsSettingTask.addOnFailureListener { exception ->
        if (exception is ResolvableApiException)
            try {
                val intentSenderRequest = IntentSenderRequest
                    .Builder(exception.resolution)
                    .build()

                onDisabled(intentSenderRequest)
            } catch (sendEx: IntentSender.SendIntentException) {

            }
    }
}

