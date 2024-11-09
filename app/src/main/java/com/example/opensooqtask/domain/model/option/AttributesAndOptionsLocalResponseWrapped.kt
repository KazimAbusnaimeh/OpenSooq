package com.example.opensooqtask.domain.model.option

import com.google.gson.annotations.SerializedName


data class AttributesAndOptionsLocalResponseWrapped(

    @SerializedName("result")
    val result: ResultLocalResponse

)