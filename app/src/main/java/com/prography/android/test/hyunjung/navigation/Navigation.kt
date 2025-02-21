package com.prography.android.test.hyunjung.navigation

import androidx.annotation.DrawableRes
import androidx.navigation.NavController
import com.prography.android.test.hyunjung.R
import com.prography.android.test.hyunjung.navigation.Destinations.Bookmark
import com.prography.android.test.hyunjung.navigation.Destinations.Home

object Destinations {
    const val Home = "home"
    const val Bookmark = "bookmark"
}

class Action(navController: NavController) {
    val home: () -> Unit = { navController.navigate(Home) }
    val bookmark: () -> Unit = { navController.navigate(Bookmark) }
}

sealed class Screen(
    val route: String,
    @DrawableRes val drawableId: Int
) {
    object Home : Screen("home", R.drawable.ic_home)
    object Bookmark : Screen("bookmark", R.drawable.ic_bookmark)
}