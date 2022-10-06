package com.example.featureauth.domain.usecase

import android.util.Patterns
import com.example.core.model.EmptyEmailException
import com.example.core.model.InvalidEmailException
import com.example.core.model.Result
import com.example.core.utils.UseCase

class ValidateUserEmailUseCase : UseCase<ValidateUserEmailUseCase.Params, Unit>() {
    override suspend fun call(params: Params): Result<Unit> {
        return when {
            params.email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(params.email).matches() -> {
                Result.Success(Unit)
            }
            params.email.isEmpty() -> {
                Result.Error(EmptyEmailException())
            }
            else -> {
                Result.Error(InvalidEmailException())
            }
        }
    }

    data class Params(
        val email: String,
    )
}