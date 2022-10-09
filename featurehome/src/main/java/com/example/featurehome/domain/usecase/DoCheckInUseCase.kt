package com.example.featurehome.domain.usecase

import com.example.core.model.Result
import com.example.featurehome.domain.model.EventUser
import com.example.featurehome.domain.repository.EventRepository

interface DoCheckInUseCase {
    suspend fun call(
        eventUser: EventUser
    ): Result<Unit>
}

class DoCheckInUseCaseImpl(private val repository: EventRepository) :
    DoCheckInUseCase {
    override suspend fun call(eventUser: EventUser): Result<Unit> {
        return repository.doCheckIn(eventUser)
    }
}