package com.example.featurehome.domain.usecase

import com.example.core.model.Result
import com.example.featurehome.domain.model.EventDetails
import com.example.featurehome.domain.repository.EventRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetEventDetailsUseCaseImplTest {
    private val repository: EventRepository = mockk()
    private lateinit var getEventDetailsUseCase: GetEventDetailsUseCaseImpl

    @Before
    fun setupMocks() {
        getEventDetailsUseCase = GetEventDetailsUseCaseImpl(repository)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a call THEN call repository`() =
        runBlocking {
            val id = 1

            coEvery {
                repository.getEventDetails(any())
            } returns mockk(relaxed = true)

            getEventDetailsUseCase.call(id)

            coVerify(exactly = 1) {
                repository.getEventDetails(any())
            }
        }

    @Test
    fun `GIVEN a call WHEN request is successfully THEN it should returns an Event Details`() =
        runBlocking {
            val id = 1
            val expected: Result.Success<EventDetails> = mockk(relaxed = true)

            coEvery {
                repository.getEventDetails(any())
            } returns expected

            val result = getEventDetailsUseCase.call(id)

            Assert.assertEquals(expected, result)
        }

    @Test
    fun `GIVEN a call WHEN request is fail THEN it should returns an Result Error`() =
        runBlocking {
            val id = 1
            val expected: Result.Error = mockk(relaxed = true)

            coEvery {
                repository.getEventDetails(any())
            } returns expected

            val result = getEventDetailsUseCase.call(id)

            Assert.assertEquals(expected, result)
        }
}