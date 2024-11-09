package com.example.opensooqtask.domain.model.category

import com.google.gson.annotations.SerializedName


data class CategoryModelLocalResponse(

    @SerializedName("has_child")
    val hasChild: Int,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("label_en")
    val name: String,

    @SerializedName("subCategories")
    val subCategories: List<SubCategoryLocalResponse>

)  