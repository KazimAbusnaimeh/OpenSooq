package com.example.opensooqtask.domain.model.attributes

import com.google.gson.annotations.SerializedName


data class FieldsLabelLocalResponse(

    @SerializedName("field_name")
    val fieldName: String,

    @SerializedName("label_en")
    val labelEn: String

)