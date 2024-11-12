package com.example.opensooqtask.compose.screens.category

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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
            topBarState(
                TopBarState(
                    true, PAGE_HEADER, Color.White, true,
                    textStyle = TextStyle(
                        fontWeight = FontWeight(700),
                        fontSize = 24.sp
                    )
                )
            )
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


