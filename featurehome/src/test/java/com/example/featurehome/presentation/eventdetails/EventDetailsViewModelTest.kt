package com.example.featurehome.presentation.eventdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.featurehome.domain.usecase.DoCheckInUseCaseImpl
import com.example.featurehome.domain.usecase.GetEventDetailsUseCaseImpl
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EventDetailsViewModelTest {
    private val getEventDetailsUseCase: GetEventDetailsUseCaseImpl = mockk()
    private val doCheckInUseCase: DoCheckInUseCaseImpl = mockk()
    private lateinit var viewModel: EventDetailsViewModel

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupMocks() {
        viewModel = EventDetailsViewModel(getEventDetailsUseCase, doCheckInUseCase)
    }

    @After
    fun resetMocks() {
        clearAllMocks()
    }

    @Test
    fun `GIVEN a getEventDetails THEN call getEventDetailsUseCase`() {
        val id = 1
        coEvery {
            getEventDetailsUseCase.call(any())
        } returns mockk(relaxed = true)

        viewModel.getEventDetails(id)

        coVerify(exactly = 1) {
            getEventDetailsUseCase.call(any())
        }
    }


    @Test
    fun `GIVEN a getEventDetails THEN it should shows is Loading State`() {
        val id = 1
        coEvery {
            getEventDetailsUseCase.call(any())
        } returns mockk(relaxed = true)

        viewModel.getEventDetails(id)

        Assert.assertTrue(viewModel.stateEventDetails.value is LoadingEventDetails)
    }
}