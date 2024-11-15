package com.example.opensooqtask.compose.screens.subcateory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.opensooqtask.compose.screens.common.CategoryAndSubCategoryItem
import com.example.opensooqtask.compose.screens.common.ContainerCard
import com.example.opensooqtask.compose.screens.common.LoadingProgressCircle
import com.example.opensooqtask.compose.screens.common.SearchTextField
import com.example.opensooqtask.compose.screens.filter.FiltersScreenRoute.Companion.navigateFiltersScreen
import com.example.opensooqtask.domain.model.category.SubCategoryLocalResponse

@Composable
fun SubCategoriesScreen(
    navHostController: NavHostController,
    viewModel: SubCategoriesViewModel = hiltViewModel()
) {
    val isLoading by viewModel.loadingState.collectAsState()
    val errorState by viewModel.errorState.collectAsState(initial = null)
    val searchQuery by viewModel.searchQueryState.collectAsState()
    val filteredSubCategories by viewModel.filteredSubCategoriesState.collectAsState()
    val category by viewModel.selectedCategory.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            LoadingProgressCircle()
        } else {
            SubCategoriesContent(
                searchQuery = searchQuery,
                filteredSubCategories = filteredSubCategories,
                errorState = errorState,
                onSearchQueryChanged = viewModel::updateSearchQuery,
                categoryName = category!!.name,
                navHostController = navHostController,
            )

        }
    }
}

@Composable
private fun SubCategoriesContent(
    searchQuery: String,
    filteredSubCategories: List<SubCategoryLocalResponse>?,
    errorState: Exception?,
    onSearchQueryChanged: (String) -> Unit,
    categoryName: String,
    navHostController: NavHostController
) {
    Column {

        Text(
            categoryName,
            fontSize = 20.sp,
            fontWeight = FontWeight(500),
            modifier = Modifier.padding(start = 16.dp)
        )
        SearchField(searchQuery, onSearchQueryChanged)

        when {

            errorState != null -> ErrorMessage(errorState.message ?: "Unknown error occurred")


            filteredSubCategories.isNullOrEmpty() -> NoCategoriesFoundMessage()

            else ->
                ContainerCard {
                    LazyColumn {
                        items(filteredSubCategories) { subCategory ->
                            SubCategoryItem(subCategory) {
                                navHostController.navigateFiltersScreen(subCategory.id.toString())
                            }
                        }
                    }
                }
        }
    }
}

@Composable
private fun SearchField(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
) {
    val searchPlaceHolder = "Search for a subcategory"
    SearchTextField(
        placeholderText = searchPlaceHolder,
        searchQuery = searchQuery,
        horizontalPadding = 20.dp,
        onSearchQueryChanged = onSearchQueryChanged
    )
}

@Composable
private fun ErrorMessage(message: String) {
    Text(text = "Error: $message")
}

@Composable
private fun NoCategoriesFoundMessage() {
    Text(text = "No categories found.")
}

@Composable
private fun SubCategoryItem(
    subCategory: SubCategoryLocalResponse,
    onSubCategoryClicked: () -> Unit
) {
    CategoryAndSubCategoryItem(subCategory.name, subCategory.icon) {
        onSubCategoryClicked()
    }
}


