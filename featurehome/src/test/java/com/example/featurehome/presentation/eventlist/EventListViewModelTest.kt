package com.example.featurehome.presentation.eventlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.featurehome.domain.usecase.GetEventListUseCaseImpl
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class EventListViewModelTest {
    private val getEventListUseCase: GetEventListUseCaseImpl = mockk()
    private lateinit var viewModel: EventListViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupMocks() {
        viewModel = EventListViewModel(getEventListUseCase)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a getEventList THEN call getEventListUseCase`() {

        coEvery {
            getEventListUseCase.call()
        } returns mockk(relaxed = true)

        viewModel.getEventList()

        coVerify(exactly = 1) {
            getEventListUseCase.call()
        }
    }


    @Test
    fun `GIVEN a getEventList THEN it should shows is Loading State`() {
        coEvery {
            getEventListUseCase.call()
        } returns mockk(relaxed = true)

        viewModel.getEventList()

        assertTrue(viewModel.stateEventList.value is Loading)
    }
}