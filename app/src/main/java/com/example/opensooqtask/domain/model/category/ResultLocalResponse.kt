package com.example.opensooqtask.domain.model.category

import com.google.gson.annotations.SerializedName


data class ResultLocalResponse(

    @SerializedName("data")
    val data: DataLocalResponse
)