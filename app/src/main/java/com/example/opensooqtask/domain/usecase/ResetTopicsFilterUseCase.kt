package com.example.opensooqtask.domain.usecase

import com.example.opensooqtask.domain.model.category.TopicFilterModel
import javax.inject.Inject

class ResetTopicsFilterUseCase @Inject constructor() {

    operator fun invoke(topicList: List<TopicFilterModel>): ArrayList<Int> {
        val indexItemReset = ArrayList<Int>()
        topicList.forEachIndexed { index, topicFilterModel ->
            topicFilterModel.optionList.forEach {
                if (it.selected) {
                    it.selected = false
                    if (!indexItemReset.contains(index))
                        indexItemReset.add(index)
                }
            }
        }
        return indexItemReset
    }

}