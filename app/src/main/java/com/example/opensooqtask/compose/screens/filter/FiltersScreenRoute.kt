package com.example.opensooqtask.compose.screens.filter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.opensooqtask.compose.app.TopBarState
import com.example.opensooqtask.compose.screens.Screen
import com.example.opensooqtask.compose.screens.base.BaseScreenRoute
import com.example.opensooqtask.ui.theme.MainBlue
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class FiltersScreenRoute(private val topBarState: (TopBarState) -> Unit) : BaseScreenRoute() {
    override fun addRoute(navGraphBuilder: NavGraphBuilder, navHostController: NavHostController) {
        navGraphBuilder.composable(
            route = Screen.Filter(FiltersScreenArgs.ID_ARG).route,
            arguments = listOf(navArgument(FiltersScreenArgs.ID_ARG) { type = NavType.StringType }),
        ) {
            SetSystemBarColor()
            topBarState(
                TopBarState(
                    true, PAGE_HEADER, MainBlue, true,
                    textStyle = TextStyle(
                        fontWeight = FontWeight(500),
                        fontSize = 24.sp
                    )
                )
            )
            FiltersScreen()
        }
    }

    @Composable
    override fun SetSystemBarColor() {
        val systemUiController = rememberSystemUiController()
        SideEffect {
            systemUiController.setStatusBarColor(
                color = MainBlue,
                darkIcons = false
            )
        }
    }

    companion object {
        private const val PAGE_HEADER = "Filters"
        fun NavHostController.navigateFiltersScreen(id: String) {
            navigate(Screen.Filter(id).route)
        }
    }
}

class FiltersScreenArgs(savedStateHandle: SavedStateHandle) {
    val id: String = checkNotNull(savedStateHandle[ID_ARG])

    companion object {
        const val ID_ARG = "id"
    }
}