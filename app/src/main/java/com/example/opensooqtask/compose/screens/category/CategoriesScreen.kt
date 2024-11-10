package com.example.opensooqtask.compose.screens.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.opensooqtask.compose.screens.common.CategoryAndSubCategoryItem
import com.example.opensooqtask.compose.screens.common.LoadingProgressCircle
import com.example.opensooqtask.compose.screens.common.SearchTextField
import com.example.opensooqtask.compose.screens.subcateory.SubCategoriesScreenRoute.Companion.navigateToSubCategories
import com.example.opensooqtask.domain.model.category.CategoryModelLocalResponse

@Composable
fun CategoriesScreen(
    navHostController: NavHostController,
    viewModel: CategoriesViewModel = hiltViewModel()
) {
    val isLoading by viewModel.loadingState.collectAsState()
    val errorState by viewModel.errorState.collectAsState(initial = null)
    val searchQuery by viewModel.searchQueryState.collectAsState()
    val filteredCategories by viewModel.filteredCategoriesState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            LoadingProgressCircle()
        } else {
            CategoriesContent(
                searchQuery = searchQuery,
                filteredCategories = filteredCategories,
                errorState = errorState,
                onSearchQueryChanged = viewModel::updateSearchQuery,
                onCategoryClick = { categoryId ->
                    navHostController.navigateToSubCategories(categoryId)
                }
            )
        }
    }

}

@Composable
private fun CategoriesContent(
    searchQuery: String,
    filteredCategories: List<CategoryModelLocalResponse>?,
    errorState: Exception?,
    onSearchQueryChanged: (String) -> Unit,
    onCategoryClick: (String) -> Unit
) {
    LazyColumn {
        item {
            SearchTextField(
                placeholderText = "Search for category or subcategory",
                searchQuery = searchQuery,
                horizontalPadding = 20.dp,
                onSearchQueryChanged = onSearchQueryChanged
            )
        }

        when {
            errorState != null -> item {
                Text(text = "Error: ${errorState.message}")
            }

            filteredCategories.isNullOrEmpty() -> item {
                Text("No categories found.")
            }

            else -> items(filteredCategories) { category ->
                CategoryAndSubCategoryItem(category.name, category.icon) {
                    onCategoryClick(category.id.toString())
                }
            }
        }
    }
}
