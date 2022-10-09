package com.example.featurehome.presentation.eventdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Result
import com.example.featurehome.domain.model.EventUser
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
        _stateEventDetails.postValue(LoadingEventDetails)
        viewModelScope.launch(dispatcher) {
            when (val result = getEventDetailsUseCase.call(id)) {
                is Result.Error -> {
                    _stateEventDetails.postValue(ErrorEventDetails(result.exception))
                }
                is Result.Success -> {
                    _stateEventDetails.postValue(SuccessEventDetails(result.data))
                }
            }
        }
    }

    fun doCheckIn(eventUser: EventUser) {
        _stateDoCheckIn.postValue(LoadingDoCheckIn)
        viewModelScope.launch(dispatcher) {
            when (val result = doCheckInUseCase.call(eventUser)) {
                is Result.Error -> {
                    _stateDoCheckIn.postValue(ErrorDoCheckIn(result.exception))
                }
                is Result.Success -> {
                    _stateDoCheckIn.postValue(SuccessDoCheckIn)
                }
            }
        }
    }
}