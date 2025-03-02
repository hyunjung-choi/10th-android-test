package com.prography.android.test.hyunjung

import HomeScreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.prography.android.test.hyunjung.navigation.Action
import com.prography.android.test.hyunjung.navigation.Destinations.Detail
import com.prography.android.test.hyunjung.navigation.Destinations.Home
import com.prography.android.test.hyunjung.navigation.Destinations.Random
import com.prography.android.test.hyunjung.navigation.Destinations.detailRoute
import com.prography.android.test.hyunjung.navigation.Screen
import com.prography.android.test.hyunjung.ui.detail.PhotoDetailScreen
import com.prography.android.test.hyunjung.ui.random.RandomScreen
import com.prography.android.test.hyunjung.ui.theme.Black
import com.prography.android.test.hyunjung.ui.theme.Gray30
import com.prography.android.test.hyunjung.ui.theme.White
import com.prography.android.test.hyunjung.ui.theme._10thandroidtestTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavCompose() {
    val items = listOf(
        Screen.Home,
        Screen.Random
    )

    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }

    _10thandroidtestTheme {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val isDetailScreen = navBackStackEntry?.destination?.route?.startsWith("detail/") == true

        Scaffold(
            topBar = {
                if (!isDetailScreen) {
                    Column {
                        TopAppBar(
                            title = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(White),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        painter = painterResource(R.drawable.img_prography_logo),
                                        contentDescription = "Prography",
                                        modifier = Modifier.padding(16.dp)
                                    )
                                }
                            }
                        )
                        HorizontalDivider(thickness = 1.dp, color = Gray30)
                    }
                }
            },
            bottomBar = {
                if (!isDetailScreen) {
                    CustomNavigationBar(
                        containerColor = Black,
                    ) {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentDestination = navBackStackEntry?.destination

                        Row {
                            items.toTypedArray().forEach { screen ->
                                NavigationBarItem(
                                    icon = {
                                        Icon(
                                            painter = painterResource(id = screen.drawableId),
                                            contentDescription = null
                                        )
                                    },
                                    selected = currentDestination?.hierarchy?.any {
                                        if (it.route?.contains("bookmark") == true) {
                                            screen.route == "bookmark"
                                        } else {
                                            it.route == screen.route
                                        }
                                    } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    colors = NavigationBarItemDefaults.colors(
                                        selectedIconColor = Color.White,
                                        unselectedIconColor = Color.White.copy(alpha = 0.3f),
                                        indicatorColor = Color.Transparent
                                    )
                                )
                            }
                        }
                    }
                }
            }
        ) { innerPadding ->

            val modifier = Modifier.padding(innerPadding)

            NavHost(
                modifier = modifier,
                navController = navController,
                startDestination = Home
            ) {
                composable(Home) {
                    HomeScreen(
                        onPhotoClick = { id ->
                            navController.navigate(detailRoute(id))
                        }
                    )
                }
                composable(Random) {
                    RandomScreen()
                }
                composable(
                    route = Detail,
                    arguments = listOf(
                        navArgument("id") {
                            type = NavType.StringType
                        }
                    )
                ) {
                    PhotoDetailScreen(
                        onBackClick = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CustomNavigationBar(
    modifier: Modifier = Modifier,
    containerColor: Color = NavigationBarDefaults.containerColor,
    contentColor: Color = MaterialTheme.colorScheme.contentColorFor(containerColor),
    tonalElevation: Dp = NavigationBarDefaults.Elevation,
    windowInsets: WindowInsets = NavigationBarDefaults.windowInsets,
    content: @Composable RowScope.() -> Unit
) {
    Surface(
        color = containerColor,
        contentColor = contentColor,
        tonalElevation = tonalElevation,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(windowInsets)
                .selectableGroup(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Row(
                modifier = Modifier.fillMaxWidth(0.7f)
            ) {
                content()
            }
        }
    }
}