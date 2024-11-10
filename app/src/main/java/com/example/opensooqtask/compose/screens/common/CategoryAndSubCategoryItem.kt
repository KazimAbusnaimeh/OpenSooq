package com.example.opensooqtask.compose.screens.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage


@Composable
fun CategoryAndSubCategoryItem(
    title: String,
    icon: String,
    onClick: () -> Unit
) {
    ItemContent(title, icon, onClick)
}

@Preview(showSystemUi = true)
@Composable
fun Preview() {
    ItemContent(
        title = "Cars and Bikes",
        icon = "https://opensooqui2.os-cdn.com/api/common/category/Autos.png"
    ) {}
}

@Composable
fun ItemContent(title: String, icon: String, onClick: () -> Unit) {
    Column(Modifier.clickable { onClick() }) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(20.dp, 0.dp, 0.dp, 10.dp)
        ) {
            ItemIcon(icon)
            Spacer(Modifier.width(16.dp))
            ItemTitle(title)
        }
        HorizontalDivider(thickness = 2.dp, color = Color.LightGray)
    }
}

@Composable
fun ItemTitle(title: String) {
    Text(title, fontSize = 20.sp, fontWeight = FontWeight(450))
}

@Composable
fun ItemIcon(icon: String) {
    AsyncImage(
        model = icon,
        contentDescription = "Icon",
        modifier = Modifier
            .size(40.dp)
            .padding(0.dp)
    )
}
