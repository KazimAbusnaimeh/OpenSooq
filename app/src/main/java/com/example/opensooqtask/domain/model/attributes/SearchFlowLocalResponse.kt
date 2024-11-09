package com.example.opensooqtask.domain.model.attributes

import com.google.gson.annotations.SerializedName


data class SearchFlowLocalResponse(

    @SerializedName("category_id")
    val categoryId: Int,

    @SerializedName("order")
    val order: List<String>
)