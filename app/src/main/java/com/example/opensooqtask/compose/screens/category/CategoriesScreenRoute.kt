package com.example.opensooqtask.compose.screens.category

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.opensooqtask.compose.app.TopBarState
import com.example.opensooqtask.compose.screens.Screen
import com.example.opensooqtask.compose.screens.base.BaseScreenRoute
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class CategoriesScreenRoute(private val topBarState: (TopBarState) -> Unit) : BaseScreenRoute() {
    override fun addRoute(navGraphBuilder: NavGraphBuilder, navHostController: NavHostController) {
        navGraphBuilder.composable(Screen.Category.route) {
            SetSystemBarColor()
            topBarState(TopBarState(true, PAGE_HEADER, Color.White, true))
            CategoriesScreen(navHostController)
        }
    }

    @Composable
    override fun SetSystemBarColor() {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setStatusBarColor(
                color = Color.White,
                darkIcons = true
            )
        }
    }

    companion object {
        private const val PAGE_HEADER = "Select Category"
        fun NavHostController.navigateToCategories() {
            navigate(Screen.Category.route)
        }
    }
}


