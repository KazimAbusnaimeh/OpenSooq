package com.example.opensooqtask.domain.model.attributes

import com.google.gson.annotations.SerializedName


data class AttributesAssignLocalResponse(

    @SerializedName("fields_labels")
    val fields_labels: List<FieldsLabelLocalResponse>,


    @SerializedName("search_flow")
    val searchFlow: List<SearchFlowLocalResponse>
)