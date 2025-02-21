package com.prography.android.test.hyunjung.navigation

import HomeScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.prography.android.test.hyunjung.ui.bookmark.BookmarkScreen
import com.prography.android.test.hyunjung.ui.bookmark.navigation.BookmarkRoute
import com.prography.android.test.hyunjung.ui.home.navigation.HomeRoute

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
        composable<BookmarkRoute> {
            BookmarkScreen()
        }
    }
}