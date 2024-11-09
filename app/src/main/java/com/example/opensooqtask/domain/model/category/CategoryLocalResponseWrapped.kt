package com.example.opensooqtask.domain.model.category

import com.google.gson.annotations.SerializedName


data class CategoryLocalResponseWrapped(

    @SerializedName("success")
    val success: Boolean,

    @SerializedName("result")
    val result: ResultLocalResponse,

    )