package com.example.opensooqtask.compose.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opensooqtask.domain.model.category.CategoryModelLocalResponse
import com.example.opensooqtask.domain.model.category.SubCategoryLocalResponse
import com.example.opensooqtask.domain.usecase.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel() {

    private val _loadingState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    private val _categoriesState = MutableStateFlow<List<CategoryModelLocalResponse>?>(null)

    private val _filteredCategoriesState = MutableStateFlow<List<CategoryModelLocalResponse>?>(null)
    val filteredCategoriesState: StateFlow<List<CategoryModelLocalResponse>?> get() = _filteredCategoriesState

    private val _searchQueryState = MutableStateFlow("")
    val searchQueryState: StateFlow<String> get() = _searchQueryState

    private val _errorState = MutableSharedFlow<Exception>()
    val errorState get() = _errorState.asSharedFlow()

    init {
        loadCategories()
    }

    private fun loadCategories() = viewModelScope.launch(Dispatchers.IO)  {
        _loadingState.value = true
        try {
            val categories = getCategoriesUseCase.invoke()
            _categoriesState.value = categories
            _filteredCategoriesState.value = categories
        } catch (exception: Exception) {
            _errorState.emit(exception)
        } finally {
            _loadingState.value = false
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQueryState.value = query
        filterCategories(query)
    }

    private fun filterCategories(query: String) {
        viewModelScope.launch {
            val categories = _categoriesState.value
            _filteredCategoriesState.value = if (query.isBlank() || categories == null) {
                categories
            } else {
                categories.filter { category ->
                    val categoryMatches = category.name.contains(query, ignoreCase = true)
                    val subCategoryMatches = category.subCategories.any { subCategory ->
                        subCategory.name.contains(query, ignoreCase = true)
                    }
                    categoryMatches || subCategoryMatches
                }
            }
        }
    }

}
