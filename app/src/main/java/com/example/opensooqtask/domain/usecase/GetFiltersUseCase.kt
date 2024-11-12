package com.example.opensooqtask.domain.usecase

import com.example.opensooqtask.data.repository.CategoriesRepository
import com.example.opensooqtask.domain.model.category.TopicFilterModel
import javax.inject.Inject

class GetFiltersUseCase @Inject constructor(
    private val repository: CategoriesRepository
) {

    suspend operator fun invoke(
        id: Int
    ): List<TopicFilterModel>? {
        val categories = repository.getCategories()

        for (category in categories) {
            val subCategory = category.subCategories.firstOrNull { it.id == id }
            if (subCategory != null) {
                return subCategory.filterTopics
            }
        }
        return null
    }
}
