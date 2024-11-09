package com.example.opensooqtask.domain.model.category

import com.google.gson.annotations.SerializedName


data class SubCategoryLocalResponse(

    @SerializedName("has_child")
    val hasChild: Int,

    @SerializedName("icon")
    val icon: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("label")
    val label: String,

    @SerializedName("label_en")
    val name: String,

    @SerializedName("order")
    val order: Int,

    @SerializedName("parent_id")
    val parentId: Int,

    var filterTopics: List<TopicFilterModel>?
) 