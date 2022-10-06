package com.example.featurehome.domain.usecase

import com.example.core.model.Result
import com.example.core.utils.UseCase
import com.example.featurehome.domain.model.EventDetails
import com.example.featurehome.domain.repository.EventRepository

class GetEventDetailsUseCase(private val repository: EventRepository) :
    UseCase<Int, Result<EventDetails>>() {
    override suspend fun call(params: Int): Result<EventDetails> {
        return repository.getEventDetails(params)
    }
}