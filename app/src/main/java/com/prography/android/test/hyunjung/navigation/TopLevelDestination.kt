package com.prography.android.test.hyunjung.navigation

import androidx.annotation.DrawableRes
import com.prography.android.test.hyunjung.R
import com.prography.android.test.hyunjung.ui.bookmark.navigation.BookmarkRoute
import com.prography.android.test.hyunjung.ui.home.navigation.HomeRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    @DrawableRes val iconId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    HOME(
        iconId = R.drawable.ic_home,
        route = HomeRoute::class,
        baseRoute = HomeRoute::class,
    ),
    BOOKMARK(
        iconId = R.drawable.ic_bookmark,
        route = BookmarkRoute::class
    )
}