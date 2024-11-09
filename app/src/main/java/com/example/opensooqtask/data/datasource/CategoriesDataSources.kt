package com.example.opensooqtask.data.datasource

import android.app.Application
import androidx.annotation.RawRes
import com.example.opensooqtask.R
import com.example.opensooqtask.domain.model.FilterLocalResponseWrapped
import com.example.opensooqtask.domain.model.attributes.AttributesAssignLocalResponseWrapped
import com.example.opensooqtask.domain.model.category.CategoryLocalResponseWrapped
import com.example.opensooqtask.domain.model.option.AttributesAndOptionsLocalResponseWrapped
import com.google.gson.Gson
import javax.inject.Inject

class CategoriesDataSources @Inject constructor(
    val context: Application, val moshi: Gson
) {

    fun getFilterLocalResponse(): FilterLocalResponseWrapped {
        val categoriseJson = getJson(R.raw.categories_and_sub_categories)
        val categoriseModel =
            getModelLocalResponses(
                CategoryLocalResponseWrapped::class.java,
                categoriseJson
            ).result.data.categories

        val attributesAssignJson = getJson(R.raw.dynamic_attributes_assign_raw)
        val attributesAssignModel =
            getModelLocalResponses(
                AttributesAssignLocalResponseWrapped::class.java,
                attributesAssignJson
            ).result.data

        val attributesAndOptionsJson = getJson(R.raw.dynamic_attributes_and_options_raw)
        val attributesAndOptionsModel =
            getModelLocalResponses(
                AttributesAndOptionsLocalResponseWrapped::class.java,
                attributesAndOptionsJson
            )
                .result.data

        return FilterLocalResponseWrapped(
            categoriseModel,
            attributesAssignModel,
            attributesAndOptionsModel
        )
    }

    private fun <T> getModelLocalResponses(type: Class<T>, categoriseJson: String) =
        moshi.fromJson(categoriseJson, type)

    private fun getJson(@RawRes id: Int): String {
        return context.resources.openRawResource(id).bufferedReader().use { it.readText() }
    }

}
