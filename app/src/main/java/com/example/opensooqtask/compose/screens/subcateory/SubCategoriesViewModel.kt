package com.example.opensooqtask.compose.screens.subcateory

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opensooqtask.core.extensions.removeCurlyBraces
import com.example.opensooqtask.domain.model.category.CategoryModelLocalResponse
import com.example.opensooqtask.domain.model.category.SubCategoryLocalResponse
import com.example.opensooqtask.domain.usecase.GetSelectedCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubCategoriesViewModel @Inject constructor(
    private val getSelectedCategoriesUseCase: GetSelectedCategoriesUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _loadingState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    private val _selectedCategory = MutableStateFlow<CategoryModelLocalResponse?>(null)
    val selectedCategory: StateFlow<CategoryModelLocalResponse?> get() = _selectedCategory

    private val _filteredSubCategoriesState =
        MutableStateFlow<List<SubCategoryLocalResponse>?>(null)
    val filteredSubCategoriesState: StateFlow<List<SubCategoryLocalResponse>?> get() = _filteredSubCategoriesState

    private val _searchQueryState = MutableStateFlow("")
    val searchQueryState: StateFlow<String> get() = _searchQueryState

    private val _errorState = MutableSharedFlow<Exception>()
    val errorState get() = _errorState.asSharedFlow()

    private val args: SubCategoriesScreenArgs = SubCategoriesScreenArgs(savedStateHandle)

    init {
        loadSubCategories()
    }

    private fun loadSubCategories() = viewModelScope.launch(Dispatchers.IO) {
        _loadingState.value = true
        try {
            val categories =
                getSelectedCategoriesUseCase.invoke(args.id.removeCurlyBraces().toInt())
            _selectedCategory.value = categories
            _filteredSubCategoriesState.value = categories?.subCategories
        } catch (exception: Exception) {
            _errorState.emit(exception)
        } finally {
            _loadingState.value = false
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQueryState.value = query
        filterSubCategories(query)
    }

    fun filterSubCategories(query: String) {
        viewModelScope.launch {
            val subCategories = _selectedCategory.value?.subCategories
            _filteredSubCategoriesState.value = if (query.isBlank() || subCategories == null) {
                subCategories
            } else {
                subCategories.filter { subCategory ->
                    subCategory.name.contains(query, ignoreCase = true)
                }
            }
        }
    }
}