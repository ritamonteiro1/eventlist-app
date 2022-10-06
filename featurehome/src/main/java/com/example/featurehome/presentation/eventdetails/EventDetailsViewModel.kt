package com.example.featurehome.presentation.eventdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Result
import com.example.featurehome.domain.model.User
import com.example.featurehome.domain.usecase.DoCheckInUseCase
import com.example.featurehome.domain.usecase.GetEventDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EventDetailsViewModel(
    private val getEventDetailsUseCase: GetEventDetailsUseCase,
    private val doCheckInUseCase: DoCheckInUseCase,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : ViewModel() {
    private val _stateEventDetails = MutableLiveData<EventDetailsState>()
    val stateEventDetails: LiveData<EventDetailsState> = _stateEventDetails

    private val _stateDoCheckIn = MutableLiveData<DoCheckInState>()
    val stateDoCheckIn: LiveData<DoCheckInState> = _stateDoCheckIn

    fun getEventDetails(id: Int) {
        _stateEventDetails.value = LoadingEventDetails
        viewModelScope.launch(dispatcher) {
            when (val result = getEventDetailsUseCase.call(GetEventDetailsUseCase.Params(id))) {
                is Result.Error -> {
                    _stateEventDetails.value = ErrorEventDetails(result.exception)
                }
                is Result.Success -> {
                    _stateEventDetails.value = SuccessEventDetails(result.data)
                }
            }
        }
    }

    fun doCheckIn(user: User) {
        _stateDoCheckIn.value = LoadingDoCheckIn
        viewModelScope.launch(dispatcher) {
            when (val result = doCheckInUseCase.call(DoCheckInUseCase.Params(user))) {
                is Result.Error -> {
                    _stateDoCheckIn.value = ErrorDoCheckIn(result.exception)
                }
                is Result.Success -> {
                    _stateDoCheckIn.value = SuccessDoCheckIn
                }
            }
        }
    }
}