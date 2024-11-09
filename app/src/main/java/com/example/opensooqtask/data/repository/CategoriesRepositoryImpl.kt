package com.example.opensooqtask.data.repository

import CategoryModel
import android.content.SharedPreferences
import com.example.opensooqtask.data.datasource.CategoriesDataSources
import com.example.opensooqtask.domain.model.FilterLocalResponseWrapped
import com.example.opensooqtask.domain.model.attributes.AttributesAssignLocalResponse
import com.example.opensooqtask.domain.model.attributes.SearchFlowLocalResponse
import com.example.opensooqtask.domain.model.category.CategoryModelLocalResponse
import com.example.opensooqtask.domain.model.category.SubCategoryLocalResponse
import com.example.opensooqtask.domain.model.category.TopicFilterModel
import com.example.opensooqtask.domain.model.category.TopicTypeModel
import com.example.opensooqtask.domain.model.option.AttributesAndOptionsLocalResponse
import io.realm.kotlin.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoriesRepositoryImpl @Inject constructor(
    private val remoteDataSource: CategoriesDataSources,
    private val realm: Realm,
    private val sharedPreferences: SharedPreferences,

    ) {

    suspend fun getCategories(): List<CategoryModelLocalResponse> {
        val filterLocalResponseWrapped = remoteDataSource.getFilterLocalResponse()
        val categoriseModel = filterLocalResponseWrapped.categoriseModel
        filterLocalResponseWrapped.categoriseModel.forEach { categoryModel ->
            categoryModel.subCategories.forEach { subCategoryModel ->

                fillSubcategoryInfo(filterLocalResponseWrapped, subCategoryModel)
            }
        }

        insertCategories(categoriseModel)


        return categoriseModel
    }

    private fun fillSubcategoryInfo(
        filterLocalResponseWrapped: FilterLocalResponseWrapped,
        subCategoryModel: SubCategoryLocalResponse
    ) {
        val attributesAssignModel = filterLocalResponseWrapped.attributesAssignLocalResponse
        val attributesAndOptionsModel = filterLocalResponseWrapped.attributesAndOptionsLocalResponse

        fillTopicFilterInfo(attributesAssignModel, subCategoryModel, attributesAndOptionsModel)
        fillTopicFilterOptions(attributesAndOptionsModel, subCategoryModel)
    }

    private fun fillTopicFilterInfo(
        attributesAssignModel: AttributesAssignLocalResponse,
        subCategoryModel: SubCategoryLocalResponse,
        attributesAndOptionsModel: AttributesAndOptionsLocalResponse
    ) {
        attributesAssignModel.searchFlow.forEach { searchFlowIt ->
            fillTopicFilterOrder(searchFlowIt, subCategoryModel)
            fillTopicFilterLiableAndId(
                searchFlowIt,
                attributesAssignModel,
                subCategoryModel,
                attributesAndOptionsModel
            )
        }
    }

    private fun fillTopicFilterOptions(
        attributesAndOptionsModel: AttributesAndOptionsLocalResponse,
        subCategoryModel: SubCategoryLocalResponse
    ) {
        attributesAndOptionsModel.options.forEach { option ->
            subCategoryModel.filterTopics?.forEach { topic ->
                if (topic.id == option.fieldId.toInt()) {
                    val temp = ArrayList(topic.optionList)
                    temp.add(option)
                    topic.optionList = temp
                }
            }
        }
    }

    private fun fillTopicFilterLiableAndId(
        searchFlowIt: SearchFlowLocalResponse,
        attributesAssignModel: AttributesAssignLocalResponse,
        subCategoryModel: SubCategoryLocalResponse,
        attributesAndOptionsModel: AttributesAndOptionsLocalResponse
    ) {
        searchFlowIt.order.forEach { order ->
            attributesAssignModel.fields_labels.forEach { fieldsLabel ->
                if (order == fieldsLabel.fieldName) subCategoryModel.filterTopics?.forEach { topicFilterLocalResponse ->
                    if (topicFilterLocalResponse.key == order) {
                        topicFilterLocalResponse.name = fieldsLabel.labelEn
                        topicFilterLocalResponse.fieldName = fieldsLabel.fieldName
                    }
                }
            }
            attributesAndOptionsModel.fields.forEach { field ->
                if (field.name == order) subCategoryModel.filterTopics?.forEach { topicLocalResponse ->
                    if (topicLocalResponse.key == order) {
                        topicLocalResponse.componentType = handelComponentName(field.dataType)
                        topicLocalResponse.id = field.id
                    }
                }
            }
        }
    }


    private fun fillTopicFilterOrder(
        searchFlowIt: SearchFlowLocalResponse,
        subCategoryModel: SubCategoryLocalResponse
    ) {
        if (searchFlowIt.categoryId == subCategoryModel.id) {
            val tempTopics = ArrayList<TopicFilterModel>()
            searchFlowIt.order.forEach { order ->
                tempTopics.add(TopicFilterModel(order))
            }
            subCategoryModel.filterTopics = tempTopics
        }
    }

    private fun handelComponentName(model: String): TopicTypeModel {
        return when (model) {
            LIST_STRING -> TopicTypeModel.LIST_STRING
            LIST_STRING_ICON -> TopicTypeModel.LIST_STRING_ICON
            LIST_NUMERIC -> TopicTypeModel.LIST_NUMERIC
            LIST_STRING_BOOLEAN -> TopicTypeModel.LIST_BOOLEAN
            else -> {
                TopicTypeModel.LIST_STRING
            }
        }
    }

    private suspend fun insertCategories(categoryModelLocalResponses: List<CategoryModelLocalResponse>) {
        if (isFirstRun()) {
            withContext(Dispatchers.IO) {
                realm.writeBlocking {
                    categoryModelLocalResponses.forEach { model ->
                        val categoryModel = CategoryModel(
                            model.id.toString(),
                            model.hasChild,
                            model.name,
                            model.icon
                        )
                        copyToRealm(categoryModel)
                    }
                }
            }
            sharedPreferences.edit().putBoolean(IS_FIRST_RUN, false).apply()
        }
    }


    fun isFirstRun() = sharedPreferences.getBoolean(IS_FIRST_RUN, true)


    companion object {
        private const val LIST_STRING = "list_string"
        private const val LIST_STRING_ICON = "list_string_icon"
        private const val LIST_NUMERIC = "list_numeric"
        private const val LIST_STRING_BOOLEAN = "list_string_boolean"
        private const val IS_FIRST_RUN = "isFirstRun"
    }
}