package com.example.opensooqtask.domain.model.option

import com.google.gson.annotations.SerializedName


data class OptionLocalResponse(

    @SerializedName("field_id")
    val fieldId: String,

    @SerializedName("id")
    val id: String,

    @SerializedName("label")
    val label: String,

    @SerializedName("label_en")
    val name: String,

    @SerializedName("option_img")
    val optionImg: String?,

    @SerializedName("order")
    val order: String,

    @SerializedName("parent_id")
    val parentId: String?,

    @SerializedName("value")
    val value: String,

    var selected: Boolean = false,

    @Transient
    var numericOptionValue: Pair<String?, String?>? = null

)