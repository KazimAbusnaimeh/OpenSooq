package com.example.opensooqtask.compose.screens.subcateory

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.opensooqtask.compose.app.TopBarState
import com.example.opensooqtask.compose.screens.Screen
import com.example.opensooqtask.compose.screens.base.BaseScreenRoute
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class SubCategoriesScreenRoute(private val topBarState: (TopBarState) -> Unit) : BaseScreenRoute() {

    override fun addRoute(navGraphBuilder: NavGraphBuilder, navHostController: NavHostController) {
        navGraphBuilder.composable(
            route = Screen.SubCategory(SubCategoriesScreenArgs.ID_ARG).route,
            arguments = listOf(
                navArgument(SubCategoriesScreenArgs.ID_ARG) { type = NavType.StringType }
            )
        ) {
            SetSystemBarColor()
            topBarState(TopBarState(true, PAGE_HEADER, Color.White, true))
            SubCategoriesScreen(navHostController)
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
        private const val PAGE_HEADER = "Select Subcategory"
        fun NavHostController.navigateToSubCategories(id: String) {
            navigate(Screen.SubCategory(id).route)
        }
    }
}

class SubCategoriesScreenArgs(savedStateHandle: SavedStateHandle) {
    val id: String = checkNotNull(savedStateHandle[ID_ARG])

    companion object {
        const val ID_ARG = "id"
    }
}