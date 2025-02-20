package com.prography.android.test.hyunjung.navigation

import MainScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prography.android.test.hyunjung.ui.main.navigation.MainRoute

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    NavHost(
        navController = rememberNavController(),
        startDestination = MainRoute,
        modifier = modifier
    ) {
        composable<MainRoute> {
            MainScreen()
        }
    }
}