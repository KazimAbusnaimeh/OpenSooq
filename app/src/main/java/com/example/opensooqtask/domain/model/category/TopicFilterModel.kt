package com.example.opensooqtask.domain.model.category

import com.example.opensooqtask.domain.model.option.OptionLocalResponse


data class TopicFilterModel(
    val key: String? = null,
    var fieldName: String? = null,
    var name: String? = null,
    var componentType: TopicTypeModel? = null,
    var id: Int? = null,
    var optionList: List<OptionLocalResponse> = emptyList(),
    var selected: Boolean = false
) 