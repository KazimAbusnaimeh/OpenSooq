package com.example.opensooqtask.domain.usecase

import com.example.opensooqtask.domain.model.category.CategoryModelLocalResponse
import com.example.opensooqtask.domain.model.category.SubCategoryLocalResponse
import javax.inject.Inject

class GetSubCategoriesUseCase @Inject constructor() {

    operator fun invoke(
        items: List<CategoryModelLocalResponse>?,
        id: Int
    ): List<SubCategoryLocalResponse>? {
        return items?.first { it.id == id }?.subCategories
    }

}