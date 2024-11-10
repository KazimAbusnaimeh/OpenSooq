package com.example.opensooqtask.compose.screens.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.opensooqtask.R

@Composable
fun SearchTextField(
    placeholderText: String,
    searchQuery: String,
    horizontalPadding: Dp = 0.dp,
    onSearchQueryChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = { query -> onSearchQueryChanged(query) },
        placeholder = { Text(text = placeholderText, color = Color.Gray, fontSize = 16.sp) },
        leadingIcon = { SearchIcon() },
        colors = searchTextFieldColors(),
        singleLine = true,
        modifier = Modifier.searchTextFieldModifier(horizontalPadding)
    )
}

@Composable
private fun SearchIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_search),
        contentDescription = "Search Icon",
        tint = Color.Gray
    )
}

@Composable
private fun searchTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = Color.Transparent,
    unfocusedBorderColor = Color.Transparent,
    focusedContainerColor = Color(0xFFF5F5F5),
    unfocusedContainerColor = Color(0xFFF5F5F5),
    cursorColor = Color.Gray,
    focusedTextColor = Color.Black,
    unfocusedTextColor = Color.Black
)

private fun Modifier.searchTextFieldModifier(horizontalPadding: Dp): Modifier {
    return this
        .fillMaxWidth()
        .padding(horizontalPadding, 8.dp)
        .shadow(4.dp, RoundedCornerShape(8.dp))
        .background(Color(0xFFF5F5F5), RoundedCornerShape(16.dp))
}
