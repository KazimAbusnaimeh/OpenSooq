package com.example.opensooqtask.domain.model.attributes

import com.google.gson.annotations.SerializedName


data class AttributesAssignLocalResponseWrapped(

    @SerializedName("result")
    val result: ResultLocalResponse,

    @SerializedName("success")
    val success: Boolean

)