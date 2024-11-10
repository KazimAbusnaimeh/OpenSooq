package com.example.opensooqtask.domain.usecase

import com.example.opensooqtask.data.repository.CategoriesRepository
import com.example.opensooqtask.domain.model.category.CategoryModelLocalResponse
import javax.inject.Inject

class GetSelectedCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepository
) {

    suspend operator fun invoke(
        id: Int
    ): CategoryModelLocalResponse? {
        return repository.getCategories()?.first { it.id == id }
    }
}