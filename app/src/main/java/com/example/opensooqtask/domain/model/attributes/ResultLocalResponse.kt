package com.example.opensooqtask.domain.model.attributes

import com.google.gson.annotations.SerializedName


data class ResultLocalResponse(

    @SerializedName("data")
    val data: AttributesAssignLocalResponse,

    )