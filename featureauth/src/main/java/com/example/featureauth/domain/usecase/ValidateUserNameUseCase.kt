package com.example.featureauth.domain.usecase

import com.example.core.model.InvalidEmailException
import com.example.core.model.Result
import com.example.core.utils.UseCase

class ValidateUserNameUseCase : UseCase<ValidateUserNameUseCase.Params, Unit>() {
    override suspend fun call(params: Params): Result<Unit> {
        return when {
            params.name?.replace("\\s".toRegex(), "")?.isEmpty() == true -> {
                Result.Error(InvalidEmailException())
            }
            else -> {
                Result.Success(Unit)

            }
        }
    }

    data class Params(
        val name: String?,
    )
}