package com.prography.android.test.hyunjung.navigation

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prography.android.test.hyunjung.ui.home.navigation.HomeRoute
import com.prography.android.test.hyunjung.ui.random.RandomScreen
import com.prography.android.test.hyunjung.ui.random.navigation.RandomRoute

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = HomeRoute
    ) {
        composable<HomeRoute> {
            HomeScreen()
        }
        composable<RandomRoute> {
            RandomScreen()
        }
    }
}