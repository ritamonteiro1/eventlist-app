package com.example.featurehome.usecase

import com.example.core.model.Result
import com.example.featurehome.domain.model.Event
import com.example.featurehome.domain.repository.EventRepository
import com.example.featurehome.domain.usecase.GetEventListUseCaseImpl
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetEventListUseCaseImplTest {
    private val repository: EventRepository = mockk()
    private lateinit var getEventListUseCase: GetEventListUseCaseImpl

    @Before
    fun setupMocks() {
        getEventListUseCase = GetEventListUseCaseImpl(repository)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a call THEN call repository`() =
        runBlocking {

            coEvery {
                repository.getEventList()
            } returns mockk(relaxed = true)

            getEventListUseCase.call(
            )

            coVerify(exactly = 1) {
                repository.getEventList()
            }
        }

    @Test
    fun `GIVEN a call WHEN request is successfully THEN it should returns an Event List`() =
        runBlocking {

            val expected: Result.Success<List<Event>> = mockk(relaxed = true)

            coEvery {
                repository.getEventList()
            } returns expected

            val result = getEventListUseCase.call()

            assertEquals(expected, result)
        }

    @Test
    fun `GIVEN a call WHEN request is fail THEN it should returns an Result Error`() =
        runBlocking {

            val expected: Result.Error = mockk(relaxed = true)

            coEvery {
                repository.getEventList()
            } returns expected

            val result = getEventListUseCase.call()

            assertEquals(expected, result)
        }

}