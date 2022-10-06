package com.example.featureauth.domain.usecase

import com.example.core.model.EmptyPasswordException
import com.example.core.model.InvalidPasswordException
import com.example.core.model.Result
import com.example.core.utils.UseCase

class ValidateUserPasswordUseCase : UseCase<ValidateUserPasswordUseCase.Params, Unit>() {
    override suspend fun call(params: Params): Result<Unit> {
        return when {
            params.password.length >= MIN_PASSWORD_LENGTH -> {
                Result.Success(Unit)
            }
            params.password.isEmpty() -> {
                Result.Error(EmptyPasswordException())
            }
            else -> {
                Result.Error(InvalidPasswordException())
            }
        }
    }

    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }

    data class Params(
        val password: String,
    )
}