package com.prography.android.test.hyunjung.navigation

import androidx.annotation.DrawableRes
import androidx.navigation.NavController
import com.prography.android.test.hyunjung.R
import com.prography.android.test.hyunjung.navigation.Destinations.Home
import com.prography.android.test.hyunjung.navigation.Destinations.Random

object Destinations {
    const val Home = "home"
    const val Random = "random"
}

class Action(navController: NavController) {
    val home: () -> Unit = { navController.navigate(Home) }
    val random: () -> Unit = { navController.navigate(Random) }
}

sealed class Screen(
    val route: String,
    @DrawableRes val drawableId: Int
) {
    object Home : Screen("home", R.drawable.ic_home)
    object Random : Screen("random", R.drawable.ic_random)
}