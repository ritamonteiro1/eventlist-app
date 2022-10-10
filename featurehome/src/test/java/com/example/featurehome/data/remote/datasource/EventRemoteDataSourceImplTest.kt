package com.example.featurehome.data.remote.datasource

import com.example.core.model.Result
import com.example.core.model.ServerErrorException
import com.example.featurehome.data.mapper.toDomain
import com.example.featurehome.data.remote.model.EventDetailsResponse
import com.example.featurehome.data.remote.model.EventResponse
import com.example.featurehome.data.remote.service.EventService
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response


@ExperimentalCoroutinesApi
class EventRemoteDataSourceImplTest {
    private val service: EventService = mockk()
    private lateinit var remoteDataSource: EventRemoteDataSourceImpl

    @Before
    fun setupMocks() {
        remoteDataSource = EventRemoteDataSourceImpl(service)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a call on getEventList THEN call service`() = runBlocking {

        coEvery {
            service.getEventList()
        } returns mockk(relaxed = true)

        remoteDataSource.getEventList()

        coVerify(exactly = 1) {
            service.getEventList()
        }
    }

    @Test
    fun `GIVEN a call on getEventDetails THEN call service`() = runBlocking {
        val id = 1

        coEvery {
            service.getEventDetails(any())
        } returns mockk(relaxed = true)

        remoteDataSource.getEventDetails(id)

        coVerify(exactly = 1) {
            service.getEventDetails(id)
        }
    }

    @Test
    fun `GIVEN a call on getEventList WHEN has a HttpException on service THEN returns ServerErrorException`() =
        runBlocking {

            val exceptionExpected = HttpException(
                Response.error<HttpException>(400, mockk(relaxed = true))
            )

            coEvery {
                service.getEventList()
            } throws exceptionExpected

            val result = remoteDataSource.getEventList()

            assertTrue(result is Result.Error && result.exception is ServerErrorException)
        }

    @Test
    fun `GIVEN a call on getEventList WHEN has a Success on service THEN returns an Event List`() =
        runBlocking {

            val eventListResponse = mockk<List<EventResponse>>(relaxed = true)

            val expected = Result.Success(eventListResponse.toDomain())

            coEvery {
                service.getEventList()
            } returns eventListResponse

            val result = remoteDataSource.getEventList() as Result.Success

            assertEquals(expected, result)
        }

    @Test
    fun `GIVEN a call on getEventDetails WHEN has a HttpException on service THEN returns ServerErrorException`() =
        runBlocking {
            val id = 1

            val exceptionExpected = HttpException(
                Response.error<HttpException>(400, mockk(relaxed = true))
            )

            coEvery {
                service.getEventDetails(any())
            } throws exceptionExpected

            val result = remoteDataSource.getEventDetails(id)

            assertTrue(result is Result.Error && result.exception is ServerErrorException)
        }

    @Test
    fun `GIVEN a call on getEventDetails WHEN has a Success on service THEN returns an Event Details`() =
        runBlocking {
            val id = 1

            val eventDetailsResponse = mockk<EventDetailsResponse>(relaxed = true)

            val expected = Result.Success(eventDetailsResponse.toDomain())

            coEvery {
                service.getEventDetails(any())
            } returns eventDetailsResponse

            val result = remoteDataSource.getEventDetails(id) as Result.Success

            assertEquals(expected, result)
        }
}