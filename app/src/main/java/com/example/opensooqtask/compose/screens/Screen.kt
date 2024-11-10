package com.example.opensooqtask.compose.screens


sealed class Screen(val route: String) {

    data object Category : Screen(CATEGORY_SCREEN)
    data class SubCategory(val id: String) : Screen("$SUB_CATEGORY_SCREEN/{$id}")

    companion object AppDestinations {
        private const val CATEGORY_SCREEN = "categoriesScreen"
        private const val SUB_CATEGORY_SCREEN = "subCategoriesScreen"
    }

}