package com.example.featureauth.domain.usecase

import com.example.core.model.EmptyPasswordException
import com.example.core.model.InvalidPasswordException
import com.example.core.model.Result

interface ValidateUserPasswordUseCase {
    fun call(password: String): Result<Unit>
}

class ValidateUserPasswordUseCaseImpl : ValidateUserPasswordUseCase {
    private companion object {
        const val MIN_PASSWORD_LENGTH = 6
    }

    override fun call(password: String): Result<Unit> {
        return when {
            password.length >= MIN_PASSWORD_LENGTH -> {
                Result.Success(Unit)
            }
            password.isEmpty() -> {
                Result.Error(EmptyPasswordException())
            }
            else -> {
                Result.Error(InvalidPasswordException())
            }
        }
    }
}