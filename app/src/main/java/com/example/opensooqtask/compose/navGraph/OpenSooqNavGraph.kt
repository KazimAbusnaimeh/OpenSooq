package com.example.opensooqtask.compose.navGraph

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.opensooqtask.compose.app.TopBarState
import com.example.opensooqtask.compose.screens.Screen
import com.example.opensooqtask.compose.screens.base.BaseScreenRoute
import com.example.opensooqtask.compose.screens.category.CategoriesScreenRoute
import com.example.opensooqtask.compose.screens.filter.FiltersScreenRoute
import com.example.opensooqtask.compose.screens.subcateory.SubCategoriesScreenRoute

@Composable
fun OpenSooqNavGraph(
    navController: NavHostController,
    topBarState: (TopBarState) -> Unit
) {
    val screenRoutes: List<BaseScreenRoute> = listOf(
        CategoriesScreenRoute(topBarState),
        SubCategoriesScreenRoute(topBarState),
        FiltersScreenRoute(topBarState)
    )

    NavHost(navController, startDestination = Screen.Category.route,

        enterTransition = { slideInHorizontally { it } + fadeIn() },
        exitTransition = { slideOutHorizontally { -it } + fadeOut() },
        popEnterTransition = { slideInHorizontally { -it } + fadeIn() },
        popExitTransition = { slideOutHorizontally { it } + fadeOut() }
    ) {
        screenRoutes.forEach { route ->
            route.addRoute(this, navController)
        }
    }
}