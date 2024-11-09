package com.example.opensooqtask.domain.usecase

import com.example.opensooqtask.domain.model.category.TopicFilterModel
import javax.inject.Inject

class GetCountFilterResultsUseCase @Inject constructor() {

    operator fun invoke(topicList: List<TopicFilterModel>): String {
        var filterResults = 0
        topicList.forEach {
            filterResults += it.optionList.size
        }
        return filterResults.toString()
    }

}