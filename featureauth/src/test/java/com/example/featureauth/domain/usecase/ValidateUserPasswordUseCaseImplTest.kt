package com.example.featureauth.domain.usecase

import com.example.core.model.Result
import org.junit.Assert.assertTrue
import org.junit.Test


class ValidateUserPasswordUseCaseImplTest {
    private val validateUserPassword = ValidateUserPasswordUseCaseImpl()

    @Test
    fun `GIVEN a blank password WHEN call use case THEN it should returns Result Error`() {
        val result = validateUserPassword.call("")

        assertTrue(result is Result.Error)
    }

    @Test
    fun `GIVEN a password with length less than 6 WHEN call use case THEN it should returns Result Error`() {
        val result = validateUserPassword.call("123")

        assertTrue(result is Result.Error)
    }

    @Test
    fun `GIVEN a valid password WHEN call use case THEN it should returns Result Success`() {
        val result = validateUserPassword.call("123456")

        assertTrue(result is Result.Success)
    }
}