package com.example.featureauth.domain.usecase

import com.example.core.model.InvalidEmailException
import com.example.core.model.Result

interface ValidateUserNameUseCase {
    fun call(name: String?): Result<Unit>
}

class ValidateUserNameUseCaseImpl : ValidateUserNameUseCase {
    override fun call(name: String?): Result<Unit> {
        return when {
            name?.replace("\\s".toRegex(), "")?.isEmpty() == true -> {
                Result.Error(InvalidEmailException())
            }
            else -> {
                Result.Success(Unit)

            }
        }
    }
}