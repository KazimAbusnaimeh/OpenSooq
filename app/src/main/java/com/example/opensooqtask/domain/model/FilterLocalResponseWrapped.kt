package com.example.opensooqtask.domain.model

import com.example.opensooqtask.domain.model.attributes.AttributesAssignLocalResponse
import com.example.opensooqtask.domain.model.category.CategoryModelLocalResponse
import com.example.opensooqtask.domain.model.option.AttributesAndOptionsLocalResponse

data class FilterLocalResponseWrapped(

    val categoriseModel: List<CategoryModelLocalResponse>,

    val attributesAssignLocalResponse: AttributesAssignLocalResponse,

    val attributesAndOptionsLocalResponse: AttributesAndOptionsLocalResponse,
) 