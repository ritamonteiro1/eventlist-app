package com.example.featurehome.domain.usecase

import com.example.core.model.Result
import com.example.core.utils.UseCase
import com.example.featurehome.domain.model.User
import com.example.featurehome.domain.repository.EventRepository

class DoCheckInUseCase(private val repository: EventRepository) :
    UseCase<DoCheckInUseCase.Params, Unit>() {
    override suspend fun call(params: Params): Result<Unit> {
        return repository.doCheckIn(params.user)
    }

    data class Params(
        val user: User
    )
}