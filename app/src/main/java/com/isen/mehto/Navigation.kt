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
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.isen.mehto.ui.models.HomeScreen
import com.isen.mehto.ui.models.SettingsScreen
import com.isen.mehto.ui.theme.Blue60
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class DrawerMenu(val route: String, val screen: @Composable () -> Unit)

val menuItems: Array<DrawerMenu> = arrayOf (
    DrawerMenu("Home") { HomeScreen() },
    DrawerMenu("Settings") { SettingsScreen() },
)

@Composable
private fun DrawerContent(
    menuItems: Array<DrawerMenu>,
    onMenuClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
    ) {
        menuItems.forEach {
            NavigationDrawerItem(
                label = { Text(text = it.route) },
                selected = false,
                onClick = { onMenuClick(it.route) },
            )
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
                DrawerContent(menuItems) { route ->
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
