package com.example.opensooqtask.domain.model.option

import com.google.gson.annotations.SerializedName


data class AttributesAndOptionsLocalResponse(

    @SerializedName("fields")
    val fields: List<FieldLocalResponse>,

    @SerializedName("options")
    val options: List<OptionLocalResponse>
)