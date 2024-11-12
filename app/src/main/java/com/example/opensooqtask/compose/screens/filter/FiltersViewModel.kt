package com.example.opensooqtask.compose.screens.filter

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.opensooqtask.compose.screens.subcateory.SubCategoriesScreenArgs
import com.example.opensooqtask.core.extensions.removeCurlyBraces
import com.example.opensooqtask.domain.model.category.TopicFilterModel
import com.example.opensooqtask.domain.usecase.GetFiltersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(
    private val getFiltersUseCase: GetFiltersUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _loadingState = MutableStateFlow(true)
    val loadingState: StateFlow<Boolean> get() = _loadingState

    private val _filterState = MutableStateFlow<List<TopicFilterModel>?>(null)
    val filtersState = _filterState.asStateFlow()

    private val _errorState = MutableSharedFlow<Exception>()
    val errorState get() = _errorState.asSharedFlow()

    private val args: SubCategoriesScreenArgs = SubCategoriesScreenArgs(savedStateHandle)

    init {
        loadFilters()
    }

    private fun loadFilters() = viewModelScope.launch(Dispatchers.IO) {
        _loadingState.value = true
        try {
            val categories = getFiltersUseCase.invoke(args.id.removeCurlyBraces().toInt())
            _filterState.value = categories
        } catch (exception: Exception) {
            _errorState.emit(exception)
        } finally {
            _loadingState.value = false
        }
    }


}