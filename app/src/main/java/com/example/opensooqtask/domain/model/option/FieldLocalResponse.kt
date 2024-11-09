package com.example.opensooqtask.domain.model.option

import com.google.gson.annotations.SerializedName


data class FieldLocalResponse(

    @SerializedName("data_type")
    val dataType: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("parent_id")
    val parentId: Int,

    @SerializedName("parent_name")
    val parentName: String?
)