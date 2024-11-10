package com.example.opensooqtask.compose.screens.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

abstract class BaseScreenRoute {
    abstract fun addRoute(navGraphBuilder: NavGraphBuilder, navHostController: NavHostController)

    @Composable
    abstract fun SetSystemBarColor()
}

