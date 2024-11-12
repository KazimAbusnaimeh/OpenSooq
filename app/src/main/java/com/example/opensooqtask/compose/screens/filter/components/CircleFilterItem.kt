package com.example.opensooqtask.compose.screens.filter.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.opensooqtask.BuildConfig
import com.example.opensooqtask.ui.theme.DarkGrey
import com.example.opensooqtask.ui.theme.LightGrey
import com.example.opensooqtask.ui.theme.MainBlue

@Composable
fun CircleFilterItem(
    showIcon: Boolean,
    value: String,
    selected: Boolean
) {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.size(60.dp)
    ) {
        Surface(value, showIcon, selected)
        CheckIcon(selected)
    }
}

@Composable
private fun Surface(value: String, showIcon: Boolean, selected: Boolean) {
    Surface(
        shape = CircleShape,
        border = if (selected) BorderStroke(2.dp, MainBlue) else null,
        color = LightGrey,
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            if (showIcon) {
                FilterIcon(value)
            } else {
                FilterText(value)
            }
        }
    }
}

@Composable
private fun FilterIcon(value: String) {
    AsyncImage(
        model = BuildConfig.IMAGE_BASE_URL + value,
        contentDescription = "Icon",
        modifier = Modifier
            .size(35.dp)
            .padding(0.dp)
    )
}

@Composable
private fun FilterText(value: String) {
    Text(
        text = value,
        fontSize = 14.sp,
        color = DarkGrey,
        textAlign = TextAlign.Center
    )
}

@Composable
private fun CheckIcon(selected: Boolean) {
    if (selected) {
        Box(
            modifier = Modifier
                .background(Color.White, CircleShape)
                .size(20.dp)
                .padding(2.dp)
                .background(MainBlue, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.Check,
                contentDescription = "Checkmark",
                tint = Color.White,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCircleFilterItem() {
    CircleFilterItem(showIcon = false, value = "H-1 Starex", false)
}
