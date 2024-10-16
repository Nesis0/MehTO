package com.isen.mehto

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.isen.mehto.ui.views.ForecastScreen
import com.isen.mehto.ui.views.SettingsScreen
import com.isen.mehto.ui.theme.Blue60
import com.isen.mehto.ui.theme.DashedDivider
import com.isen.mehto.ui.views.MapsScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class DrawerMenu(val displayName: String, val route: String, val screen: @Composable () -> Unit)

val menuItems: Array<DrawerMenu> = arrayOf (
    DrawerMenu("Home", "weather") { ForecastScreen() },
    DrawerMenu("Maps", "maps") { MapsScreen() },
    DrawerMenu("Settings", "settings") { SettingsScreen() },
)

@Composable
private fun DrawerContent(
    drawerState: DrawerState,
    menuItems: Array<DrawerMenu>,
    onMenuClick: (String) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
    ) {
        IconButton(onClick = { coroutineScope.launch { drawerState.close() } }) {
            Icon(Icons.Filled.Close, contentDescription = "Menu")
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalDivider(
                thickness = 2f.dp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth(0.5f)
            )
        }

        menuItems.forEach {
            NavigationDrawerItem(
                label = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) { Text(text = it.displayName) }
                },
                selected = false,
                onClick = { onMenuClick(it.route) },
                colors = NavigationDrawerItemDefaults.colors(unselectedContainerColor = Color.Transparent),
            )
            DashedDivider()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(drawerState: DrawerState) {
    val coroutineScope = rememberCoroutineScope()

    TopAppBar(
        modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Max),
        navigationIcon = {
            IconButton(onClick = { coroutineScope.launch { drawerState.open() } }) {
                Icon(Icons.Filled.Menu, contentDescription = "Menu")
            }
        },
        title = { },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Blue60,
            navigationIconContentColor = Color.White
        ),
        actions = {
            Image(
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = "Application Logo",
            )
        },
    )
}

@Composable
fun Navigation(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerContent(drawerState, menuItems) { route ->
                    coroutineScope.launch { drawerState.close() }
                    navController.navigate(route)
                }
            }
        }
    ) {
        Scaffold(
            topBar = { CustomAppBar( drawerState = drawerState ) }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .background(Blue60),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                NavHost(navController = navController, startDestination = menuItems[0].route) {
                    menuItems.forEach { item ->
                        composable(item.route) { item.screen() }
                    }
                }
            }
        }
    }
}
