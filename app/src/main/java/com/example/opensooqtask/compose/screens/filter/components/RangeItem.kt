package com.example.opensooqtask.compose.screens.filter.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.opensooqtask.domain.model.option.OptionLocalResponse

@Composable
fun RangeItem(optionList: List<OptionLocalResponse>, onClick:()->Unit) {
    Column(Modifier.fillMaxWidth().clickable { onClick() }) {
        RangeRow("From", optionList.lastOrNull { it.selected }?.value ?: "Any")
        HorizontalDivider(thickness = 1.dp, color = LightGray)
        RangeRow("To", optionList.firstOrNull { it.selected }?.value ?: "Any")
    }
}

@Composable
private fun RangeRow(label: String, value: String) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(10.dp, 20.dp), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        RangeText(label)
        RangeText(value)
    }
}

@Composable
private fun RangeText(label: String) {
    Text(label, fontWeight = FontWeight(500))
}