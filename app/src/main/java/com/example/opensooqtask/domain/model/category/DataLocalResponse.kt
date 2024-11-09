package com.example.opensooqtask.domain.model.category

import com.google.gson.annotations.SerializedName


data class DataLocalResponse(

    @SerializedName("items")
    val categories: List<CategoryModelLocalResponse>

)