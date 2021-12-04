import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Dashboard
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rozatorii_bulbucasi.savewaste.R
import com.rozatorii_bulbucasi.savewaste.presentation.theme.Green400
import com.rozatorii_bulbucasi.savewaste.data.common.Screens

@Composable
fun BottomNavigationBar(
    onHomeClick: () -> Unit,
    onMapsClick: () -> Unit,
    navController: NavController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        BottomNavigationItem(
            selected = currentDestination?.hierarchy?.any { it.route == Screens.MapsScreenRoute.route } == true,
            onClick = onMapsClick,
            icon = {
                Icon(
                    Icons.Rounded.LocationOn,
                    contentDescription = stringResource(id = R.string.maps)
                )
            },
            selectedContentColor = Green400,
            unselectedContentColor = Color.LightGray,
            alwaysShowLabel = currentDestination?.hierarchy?.any { it.route == Screens.MapsScreenRoute.route } == true,
            label = {
                Text(
                    text = if (currentDestination?.hierarchy?.any { it.route == Screens.MapsScreenRoute.route } == true)
                        stringResource(id = R.string.maps)
                    else ""
                )
            }
        )

        BottomNavigationItem(
            selected = currentDestination?.hierarchy?.any { it.route == Screens.HomeScreenRoute.route } == true,
            onClick = onHomeClick,
            icon = {
                Icon(
                    Icons.Rounded.Dashboard,
                    contentDescription = stringResource(id = R.string.home)
                )
            },
            selectedContentColor = Green400,
            unselectedContentColor = Color.LightGray,
            alwaysShowLabel = currentDestination?.hierarchy?.any { it.route == Screens.HomeScreenRoute.route } == true,
            label = {
                Text(
                    text = if (currentDestination?.hierarchy?.any { it.route == Screens.HomeScreenRoute.route } == true)
                        stringResource(id = R.string.home)
                    else ""
                )
            }
        )
    }
}