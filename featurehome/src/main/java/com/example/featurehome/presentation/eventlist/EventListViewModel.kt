package com.example.featurehome.presentation.eventlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.model.Result
import com.example.featurehome.domain.usecase.GetEventListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class EventListViewModel(
    private val getEventListUseCase: GetEventListUseCase,
    private val dispatcher: CoroutineContext = Dispatchers.IO
) : ViewModel() {
    private val _stateEventList = MutableLiveData<EventListState>()
    val stateEventList: LiveData<EventListState> = _stateEventList

    fun getEventList() {
        _stateEventList.value = Loading
        viewModelScope.launch(dispatcher) {
            when (val result = getEventListUseCase.call()) {
                is Result.Error -> {
                    _stateEventList.value = Error(result.exception)
                }
                is Result.Success -> {
                    _stateEventList.value = Success(result.data)
                }
            }
        }
    }
}