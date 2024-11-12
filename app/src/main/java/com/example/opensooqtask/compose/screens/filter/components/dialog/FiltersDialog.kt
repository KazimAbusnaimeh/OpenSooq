package com.example.opensooqtask.compose.screens.filter.components.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.opensooqtask.compose.screens.common.SearchIcon
import com.example.opensooqtask.domain.model.option.OptionLocalResponse
import com.example.opensooqtask.ui.theme.LightGrey
import com.example.opensooqtask.ui.theme.MainBlue
import com.example.opensooqtask.ui.theme.MainGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FiltersDialog(
    showDialog: Boolean,
    searchQuery: String,
    optionsList: List<OptionLocalResponse>,
    itemContent: @Composable (OptionLocalResponse) -> Unit,
    showTabs: Boolean = false,
    initialIsFirstTab: Boolean = true,
    onTabChanged: (Boolean) -> Unit = {},
    onDismiss: () -> Unit,
    onReset: () -> Unit = {},
    onDone: () -> Unit = {},
) {
    var isFirstTab by remember { mutableStateOf(initialIsFirstTab) }

    if (showDialog) {
        Surface(modifier = Modifier.padding(0.dp)) {
            AlertDialog(
                onDismissRequest = onDismiss,
                content = {
                    FiltersDialogContent(
                        placeholderText = "text",
                        searchQuery = searchQuery,
                        optionsList = optionsList,
                        showTabs = showTabs,
                        isFirstTab = isFirstTab,
                        onDismiss = onDismiss,
                        onReset = onReset,
                        onDone = onDone,
                        onTabChanged = { selectedTab ->
                            isFirstTab = selectedTab
                            onTabChanged(selectedTab) // Notify the parent
                        },
                        itemContent = itemContent,
                        onSearchQueryChanged = {})
                },
            )
        }
    }
}

@Composable
private fun FiltersDialogContent(
    placeholderText: String,
    searchQuery: String,
    showTabs: Boolean,
    isFirstTab: Boolean,
    optionsList: List<OptionLocalResponse>,
    itemContent: @Composable (OptionLocalResponse) -> Unit,
    onDismiss: () -> Unit,
    onReset: () -> Unit,
    onDone: () -> Unit,
    onTabChanged: (Boolean) -> Unit,
    onSearchQueryChanged: (String) -> Unit
) {
    BoxWithConstraints {
        val parentHeight = maxHeight * 0.8f
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(parentHeight)
                .background(color = Color.White, shape = RoundedCornerShape(4.dp))
        ) {
            // header
            HeaderSearchField(searchQuery, onSearchQueryChanged, placeholderText)
            if (showTabs)
                HeaderTabs(isFirstTab, onTabChanged)
            HeaderTitle()

            // content
            LazyColumn(Modifier.weight(1.0f)) {
                items(optionsList) {
                    itemContent(it)
                }
            }

            // footer
            Row {
                FooterButton("Cancel", Modifier.weight(0.1f), false, showTabs) { onDismiss() }
                if (!showTabs) {
                    FooterButton("Reset", Modifier.weight(0.1f), false, showTabs) { onReset() }
                    FooterButton("Done", Modifier.weight(0.1f), true, showTabs) { onDone() }
                }
            }
        }
    }
}

@Composable
private fun HeaderTabs(isFirstTab: Boolean, onTabChanged: (Boolean) -> Unit) {
    Row {
        DialogTabs("FROM", Modifier.weight(1.0f), isFirstTab) {
            onTabChanged(true)
        }
        DialogTabs("TO", Modifier.weight(1.0f), !isFirstTab) {
            onTabChanged(false)
        }
    }
}

@Composable
private fun HeaderTitle() {
    Row(
        Modifier
            .background(color = LightGrey)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(text = "Car Maker", fontSize = 14.sp, fontWeight = FontWeight(600))
    }
}

@Composable
private fun HeaderSearchField(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    placeholderText: String
) {
    OutlinedTextField(
        value = searchQuery,
        onValueChange = { query -> onSearchQueryChanged(query) },
        placeholder = { Text(text = placeholderText, color = Color.Gray, fontSize = 16.sp) },
        leadingIcon = { SearchIcon() },
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
        ),
    )
}

@Composable
fun DialogTabs(
    text: String,
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(modifier = modifier
        .padding(bottom = 8.dp)
        .clickable { onClick() }) {
        Text(
            text,
            color = if (selected) MainBlue else Color.LightGray,
            fontSize = 16.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            textAlign = TextAlign.Center
        )
        HorizontalDivider(thickness = 2.dp, color = if (selected) MainBlue else Color.White)
    }
}

@Composable
private fun FooterButton(
    text: String,
    modifier: Modifier = Modifier,
    isDone: Boolean,
    isAlone: Boolean,
    onClick: () -> Unit
) {
    Text(
        text,
        modifier
            .background(if (isDone) MainGreen else Color.White)
            .padding(horizontal = 8.dp)
            .height(50.dp)
            .fillMaxHeight()
            .wrapContentHeight()
            .clickable { onClick() },
        style = TextStyle(
            fontWeight = FontWeight(700),
            fontSize = 18.sp,
            color = if (isDone) Color.White else if (isAlone) MainBlue else Color.Black
        ),
        textAlign = if (isDone) TextAlign.Center else if (isAlone) TextAlign.End else TextAlign.Start
    )
}




