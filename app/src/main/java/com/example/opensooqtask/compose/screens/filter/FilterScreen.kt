package com.example.opensooqtask.compose.screens.filter

import AdaptiveSelectGrid
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.opensooqtask.compose.screens.common.ContainerCard
import com.example.opensooqtask.compose.screens.common.LoadingProgressCircle
import com.example.opensooqtask.compose.screens.filter.components.CircleFilterItem
import com.example.opensooqtask.compose.screens.filter.components.FilterCardFooter
import com.example.opensooqtask.compose.screens.filter.components.FilterCardHeader
import com.example.opensooqtask.compose.screens.filter.components.RangeItem
import com.example.opensooqtask.compose.screens.filter.components.dialog.DialogRangeItem
import com.example.opensooqtask.compose.screens.filter.components.dialog.DialogStringIconItem
import com.example.opensooqtask.compose.screens.filter.components.dialog.FiltersDialog
import com.example.opensooqtask.domain.model.category.TopicFilterModel
import com.example.opensooqtask.domain.model.category.TopicTypeModel
import com.example.opensooqtask.domain.model.option.OptionLocalResponse

@Composable
fun FiltersScreen(
    viewModel: FiltersViewModel = hiltViewModel()
) {
    val isLoading by viewModel.loadingState.collectAsState()
    val errorState by viewModel.errorState.collectAsState(initial = null)
    val filtersState by viewModel.filtersState.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            LoadingProgressCircle()
        } else {
            FiltersContent(
                filteredSubCategories = filtersState,
                errorState = errorState,
            )
        }
    }
}

@Composable
private fun FiltersContent(
    filteredSubCategories: List<TopicFilterModel>?,
    errorState: Exception?,
) {
    Column {
        when {
            errorState != null -> ErrorMessage(errorState.message ?: "Unknown error occurred")
            filteredSubCategories.isNullOrEmpty() -> NoCategoriesFoundMessage()
            else -> LazyColumn(Modifier.padding(top = 10.dp)) {
                items(filteredSubCategories) { filter ->
                    if (filter.optionList.isNotEmpty()) {
                        FilterCard(filter)
                    }
                }
            }
        }
    }
}

@Composable
private fun FilterCard(filter: TopicFilterModel) {
    if (filter.componentType !== null) {
        ContainerCard {
            var showDialog by remember { mutableStateOf(false) }
            var optionsList by remember { mutableStateOf(emptyList<OptionLocalResponse>()) }
            var itemContent: @Composable (OptionLocalResponse) -> Unit = {}
            var showTabs by remember { mutableStateOf(false) }
            Column {
                FilterCardHeader(checkNotNull(filter.name), filter.componentType != TopicTypeModel.LIST_BOOLEAN)

                FilterComponentTypeHandler(
                    filter,
                    onOptionsReady = {
                        optionsList = filter.optionList
                        showTabs = true
                        showDialog = true
                    },
                    onItemContentReady = {
                        itemContent = it
                    }
                )

                FilterCardFooter(filter.componentType == TopicTypeModel.LIST_STRING || filter.componentType == TopicTypeModel.LIST_STRING_ICON, filter) {
                    optionsList = filter.optionList
                    showTabs = false
                    showDialog = true
                }
                FiltersDialog(
                    showDialog = showDialog,
                    searchQuery = "",
                    optionsList = optionsList,
                    showTabs = showTabs,
                    itemContent = itemContent,
                    onDismiss = { showDialog = false })
            }
        }
    }
}

@Composable
private fun FilterComponentTypeHandler(
    filter: TopicFilterModel,
    onOptionsReady: () -> Unit,
    onItemContentReady: (itemContent: @Composable (OptionLocalResponse) -> Unit) -> Unit
) {
    when (filter.componentType) {
        TopicTypeModel.LIST_BOOLEAN -> {
            Box(Modifier.padding(10.dp)) {
                AdaptiveSelectGrid(filter.optionList.map { it.name })
            }
        }

        TopicTypeModel.LIST_NUMERIC -> {
            RangeItem(filter.optionList) {
                onOptionsReady()
            }
            onItemContentReady { DialogRangeItem(it) }
        }

        TopicTypeModel.LIST_STRING_ICON -> {
            ListStringAndIconsFilter(true, filter)
            onItemContentReady { DialogStringIconItem(it, true) }
        }

        TopicTypeModel.LIST_STRING -> {
            ListStringAndIconsFilter(false, filter)
            onItemContentReady { DialogStringIconItem(it, false) }
        }

        else -> {}
    }
}

@Composable
private fun ListStringAndIconsFilter(showIcons: Boolean, filter: TopicFilterModel) {
    LazyRow(Modifier.padding(8.dp, 12.dp)) {
        items(filter.optionList) { option ->
            CircleFilterItem(
                showIcons,
                if (showIcons) checkNotNull(option.optionImg) else option.name,
                option.selected
            )
            Spacer(Modifier.width(8.dp))
        }
    }
}

@Composable
private fun ErrorMessage(message: String) {
    Text(text = "Error: $message")
}

@Composable
private fun NoCategoriesFoundMessage() {
    Text(text = "No categories found.")
}



