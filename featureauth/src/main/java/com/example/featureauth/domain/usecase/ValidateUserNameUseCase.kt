package com.example.featureauth.domain.usecase

import com.example.core.model.EmptyNameException
import com.example.core.model.InvalidEmailException
import com.example.core.model.InvalidNameException
import com.example.core.model.Result
import com.example.featureauth.domain.repository.UserRepository

interface ValidateUserNameUseCase {
    fun call(name: String): Result<Unit>
}

class ValidateUserNameUseCaseImpl(private val userRepository: UserRepository) : ValidateUserNameUseCase {
    override fun call(name: String): Result<Unit> {
        return when {
            name.isEmpty() -> {
                Result.Error(EmptyNameException())
            }
            name.replace("\\s".toRegex(), "").isEmpty() -> {
                Result.Error(InvalidNameException())
            }
            else -> {
                userRepository.saveUserName(name)
                Result.Success(Unit)
            }
        }
    }
}