package com.example.featurehome.domain.usecase

import com.example.core.model.Result
import com.example.core.utils.UseCase
import com.example.featurehome.domain.model.Event
import com.example.featurehome.domain.repository.EventRepository

class GetEventListUseCase(private val repository: EventRepository) :
    UseCase<GetEventListUseCase.Params, List<Event>>() {

    override suspend fun call(params: Params): Result<List<Event>> {
        return repository.getEventList()
    }

    object Params
}