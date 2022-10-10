package com.example.featurehome.data.repository

import com.example.core.model.Result
import com.example.datalocal.datasource.EventCacheDataSource
import com.example.datalocal.datasource.EventUserCacheDataSource
import com.example.datalocal.model.EventDao
import com.example.featurehome.data.remote.datasource.EventRemoteDataSource
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
class EventRepositoryImplTest {
    private val remoteDataSource: EventRemoteDataSource = mockk()
    private val cacheDataSource: EventCacheDataSource = mockk()
    private val eventUserCacheDataSource: EventUserCacheDataSource = mockk()
    private lateinit var repository: EventRepositoryImpl

    @Before
    fun setupMocks() {
        repository =
            EventRepositoryImpl(remoteDataSource, cacheDataSource, eventUserCacheDataSource)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a call on getEventList THEN call remoteDataSource`() =
        runBlocking {

            coEvery {
                remoteDataSource.getEventList()
            } returns mockk(relaxed = true)

            coEvery {
                cacheDataSource.getEventList()
            } returns mockk(relaxed = true)

            repository.getEventList()

            coVerify(exactly = 1) {
                remoteDataSource.getEventList()
            }
        }

    @Test
    fun `GIVEN a call on getEventList WHEN request is successfully and Event List is empty THEN it should returns a Result Error`() =
        runBlocking {

            val emptyList = emptyList<EventDao>()

            val expectedError: Result.Error = mockk(relaxed = true)

            coEvery {
                remoteDataSource.getEventList()
            } returns expectedError

            coEvery {
                cacheDataSource.getEventList()
            } returns emptyList

            val result = repository.getEventList()

            assertEquals(expectedError, result)
        }

    @Test
    fun `GIVEN a call on getEventDetails THEN call remoteDataSource`() =
        runBlocking {
            val id = 1
            coEvery {
                remoteDataSource.getEventDetails(any())
            } returns mockk(relaxed = true)

            coEvery {
                cacheDataSource.getEventDetails(any())
            } returns mockk(relaxed = true)

            repository.getEventDetails(id)

            coVerify(exactly = 1) {
                remoteDataSource.getEventDetails(any())
            }
        }

    @Test
    fun `GIVEN a call on getEventDetails WHEN request is successfully and Event Details is null THEN it should returns a Result Error`() =
        runBlocking {
            val id = 1
            val expectedError: Result.Error = mockk(relaxed = true)

            coEvery {
                remoteDataSource.getEventDetails(any())
            } returns expectedError

            coEvery {
                cacheDataSource.getEventDetails(any())
            } returns null

            val result = repository.getEventDetails(id)

            assertEquals(expectedError, result)
        }
}