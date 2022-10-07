package com.example.featurehome.domain.usecase

import com.example.core.model.Result
import com.example.featurehome.domain.model.EventDetails
import com.example.featurehome.domain.repository.EventRepository


interface GetEventDetailsUseCase {
    suspend fun call(
        eventId: Int
    ): Result<EventDetails>
}

class GetEventDetailsUseCaseImpl(private val repository: EventRepository) :
    GetEventDetailsUseCase {
    override suspend fun call(eventId: Int): Result<EventDetails> {
        return repository.getEventDetails(eventId)
    }
}