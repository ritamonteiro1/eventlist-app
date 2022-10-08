package com.example.featurehome.domain.repository

import com.example.core.model.Result
import com.example.featurehome.domain.model.Event
import com.example.featurehome.domain.model.EventDetails
import com.example.featurehome.domain.model.EventUser

interface EventRepository {
    suspend fun getEventList(
    ): Result<List<Event>>

    suspend fun getEventDetails(
        id: Int,
    ): Result<EventDetails>

    suspend fun doCheckIn(
        eventUser: EventUser
    ): Result<Unit>
}