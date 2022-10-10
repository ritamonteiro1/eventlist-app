package com.example.featureauth.domain.usecase

import com.example.core.model.Result
import com.example.featureauth.domain.repository.UserRepository
import io.mockk.mockk
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidateUserEmailUseCaseImplTest {
    private val repository: UserRepository = mockk()
    private val validateUserEmail = ValidateUserEmailUseCaseImpl(repository)

    @Test
    fun `GIVEN a blank email WHEN call use case THEN it should returns Result Error`() {
        val result = validateUserEmail.call("")

        assertTrue(result is Result.Error)
    }

    @Test
    fun `GIVEN an invalid email WHEN call use case THEN it should returns Result Error`() {
        val result = validateUserEmail.call("test")

        assertTrue(result is Result.Error)
    }

    @Test
    fun `GIVEN a valid email WHEN call use case THEN it should returns Result Success`() {
        val result = validateUserEmail.call("test@test.com.br")

        assertTrue(result is Result.Success)
    }
}