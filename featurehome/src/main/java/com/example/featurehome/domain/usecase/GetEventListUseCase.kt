package com.example.featurehome.domain.usecase

import com.example.core.model.Result
import com.example.featurehome.domain.model.Event
import com.example.featurehome.domain.repository.EventRepository

interface GetEventListUseCase {
    suspend fun call(
    ): Result<List<Event>>
}

class GetEventListUseCaseImpl(private val repository: EventRepository) :
    GetEventListUseCase {

    override suspend fun call(): Result<List<Event>> {
        return repository.getEventList()
    }

}