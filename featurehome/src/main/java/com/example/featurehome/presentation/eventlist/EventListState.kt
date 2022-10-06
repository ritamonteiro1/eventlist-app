package com.example.featurehome.presentation.eventlist

import com.example.featurehome.domain.model.Event

sealed class EventListState

object Loading : EventListState()

class Success(val eventList: List<Event>) : EventListState()

class Error(val error: Throwable) : EventListState()