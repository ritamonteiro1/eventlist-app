package com.example.featureauth.domain.usecase

import android.util.Patterns
import com.example.core.model.EmptyEmailException
import com.example.core.model.InvalidEmailException
import com.example.core.model.Result

interface ValidateUserEmailUseCase {
    fun call(email: String): Result<Unit>
}

class ValidateUserEmailUseCaseImpl : ValidateUserEmailUseCase {
    override fun call(email: String): Result<Unit> {
        return when {
            email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                Result.Success(Unit)
            }
            email.isEmpty() -> {
                Result.Error(EmptyEmailException())
            }
            else -> {
                Result.Error(InvalidEmailException())
            }
        }
    }
}