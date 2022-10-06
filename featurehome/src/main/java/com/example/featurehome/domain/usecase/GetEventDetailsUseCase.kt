package com.example.featurehome.domain.usecase

import com.example.core.model.Result
import com.example.core.utils.UseCase
import com.example.featurehome.domain.model.EventDetails
import com.example.featurehome.domain.repository.EventRepository

class GetEventDetailsUseCase(private val repository: EventRepository) :
    UseCase<GetEventDetailsUseCase.Params, EventDetails>() {
    override suspend fun call(params: Params): Result<EventDetails> {
        return repository.getEventDetails(params.id)
    }

    data class Params(
        val id: Int
    )
}