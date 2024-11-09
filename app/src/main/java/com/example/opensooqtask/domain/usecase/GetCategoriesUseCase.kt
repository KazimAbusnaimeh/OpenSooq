package com.example.opensooqtask.domain.usecase

import com.example.opensooqtask.data.repository.CategoriesRepositoryImpl
import com.example.opensooqtask.domain.model.category.CategoryModelLocalResponse
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repository: CategoriesRepositoryImpl
) {

    suspend operator fun invoke(): List<CategoryModelLocalResponse> {
        return repository.getCategories()
    }

}