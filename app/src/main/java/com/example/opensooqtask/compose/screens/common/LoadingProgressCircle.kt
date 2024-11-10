package com.example.opensooqtask.compose.screens.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoadingProgressCircle() {
    Box(
        Modifier.fillMaxWidth().fillMaxHeight(), contentAlignment = Alignment.Center,
        content = { CircularProgressIndicator(Modifier.size(100.dp)) })
}