package com.example.featurehome.domain.usecase

import com.example.core.model.Result
import com.example.core.utils.UseCase
import com.example.featurehome.domain.model.User
import com.example.featurehome.domain.repository.EventRepository

class DoCheckInUseCase(private val repository: EventRepository) :
    UseCase<User, Result<Unit>>() {
    override suspend fun call(params: User): Result<Unit> {
        return repository.doCheckIn(params)
    }
}