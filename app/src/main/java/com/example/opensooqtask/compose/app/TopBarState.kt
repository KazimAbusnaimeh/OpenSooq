package com.example.opensooqtask.compose.app

import androidx.compose.ui.graphics.Color

data class TopBarState(
    val showAppBar: Boolean = false,
    val title: String = "",
    val backgroundColor: Color = Color.White,
    val showBackButton: Boolean = false,
    val darkIcons: Boolean = true
)