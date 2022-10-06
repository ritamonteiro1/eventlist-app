package com.example.featurehome.presentation.eventdetails

import com.example.featurehome.domain.model.EventDetails

sealed class EventDetailsState

object LoadingEventDetails : EventDetailsState()

class SuccessEventDetails(val eventDetails: EventDetails) : EventDetailsState()

class ErrorEventDetails(val error: Throwable) : EventDetailsState()