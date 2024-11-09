package com.example.opensooqtask.domain.model.option

import com.google.gson.annotations.SerializedName


data class ResultLocalResponse(

    @SerializedName("data")
    val data: AttributesAndOptionsLocalResponse,

    @SerializedName("hash")
    val hash: String,

    @SerializedName("status")
    val status: Int
)