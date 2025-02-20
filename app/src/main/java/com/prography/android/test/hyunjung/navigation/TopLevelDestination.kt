package com.prography.android.test.hyunjung.navigation

import androidx.annotation.DrawableRes
import com.prography.android.test.hyunjung.R
import com.prography.android.test.hyunjung.ui.main.navigation.MainRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    @DrawableRes val iconId: Int,
    val route: KClass<*>,
    val baseRoute: KClass<*> = route,
) {
    MAIN(
        iconId = R.drawable.ic_main,
        route = MainRoute::class,
    ),
//    BOOKMARK(
//        iconId = R.drawable.ic_bookmark,
//        route = BookmarksRoute::class,
//    )
}