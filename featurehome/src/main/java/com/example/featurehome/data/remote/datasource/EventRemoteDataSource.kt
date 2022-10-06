package com.example.featurehome.data.remote.datasource

import com.example.core.model.Result
import com.example.featurehome.domain.model.Event
import com.example.featurehome.domain.model.EventDetails
import com.example.featurehome.domain.model.User

interface EventRemoteDataSource {
    suspend fun getEventList(
    ): Result<List<Event>>

    suspend fun getEventDetails(
        id: Int,
    ): Result<EventDetails>

    suspend fun doCheckIn(
        user: User,
    ): Result<Unit>
}