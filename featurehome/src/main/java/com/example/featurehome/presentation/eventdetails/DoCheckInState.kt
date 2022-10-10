package com.example.featurehome.presentation.eventdetails


sealed class DoCheckInState

object SuccessDoCheckIn : DoCheckInState()

class ErrorDoCheckIn(val error: Throwable) : DoCheckInState()