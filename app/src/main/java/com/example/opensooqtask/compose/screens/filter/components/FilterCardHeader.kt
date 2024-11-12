package com.example.opensooqtask.compose.screens.filter.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FilterCardHeader(text: String, showDivider: Boolean) {
    Column {
        Text(
            text,
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier.padding(12.dp, 8.dp)
        )
        if (showDivider) HorizontalDivider(thickness = 1.dp, color = LightGray)
    }
}