package com.example.featurehome.domain.usecase

import com.example.core.model.NullCacheException
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
        val result = repository.getEventList()
        if (result is Result.Success && result.data.isNotEmpty()) {
            return Result.Success(result.data)
        }
        return Result.Error(NullCacheException())
    }
}