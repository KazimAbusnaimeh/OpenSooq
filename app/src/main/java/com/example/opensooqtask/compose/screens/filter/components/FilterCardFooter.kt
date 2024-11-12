package com.example.opensooqtask.compose.screens.filter.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.opensooqtask.R
import com.example.opensooqtask.domain.model.category.TopicFilterModel
import com.example.opensooqtask.ui.theme.DarkGrey

@Composable
fun FilterCardFooter(showFooter: Boolean, filter: TopicFilterModel, onClick: () -> Unit) {
    if (showFooter) {
        FooterCardContent(getSelectedOptionsText(filter)) { onClick() }
    }
}

@Composable
fun FooterCardContent(text: Pair<String, Boolean>, onClick: () -> Unit) {
    Column {
        HorizontalDivider(thickness = 1.dp, color = Color.LightGray)
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 8.dp)
                .clickable { onClick() }
        ) {
            Text(
                text.first,
                fontSize = 16.sp,
                fontWeight = FontWeight(500),
                color = if (text.second) DarkGrey else Color.Black
            )
            nextIcon()
        }
    }

}

@Composable
private fun nextIcon() {
    Icon(
        painter = painterResource(id = R.drawable.ic_next),
        contentDescription = "Next Icon",
        modifier = Modifier
            .padding(0.dp)
            .size(30.dp),
    )
}

private fun getSelectedOptionsText(filter: TopicFilterModel): Pair<String, Boolean> {
    val selectedOptions = filter.optionList.filter { it.selected }

    if (selectedOptions.isNotEmpty()) {
        return Pair(selectedOptions.joinToString(", ") { it.name }, false)
    } else {
        return Pair(filter.name.toString(), true)
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    FooterCardContent(Pair("modal", true), {})
}
