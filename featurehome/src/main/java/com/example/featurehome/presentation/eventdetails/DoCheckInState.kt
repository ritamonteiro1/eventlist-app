package com.example.featurehome.presentation.eventdetails


sealed class DoCheckInState

object LoadingDoCheckIn : DoCheckInState()

object SuccessDoCheckIn : DoCheckInState()

class ErrorDoCheckIn(val error: Throwable) : DoCheckInState()