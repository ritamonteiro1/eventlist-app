package com.example.featurehome.data.repository

import com.example.core.model.Result
import com.example.featurehome.data.remote.datasource.EventRemoteDataSource
import com.example.featurehome.domain.model.Event
import com.example.featurehome.domain.model.EventDetails
import com.example.featurehome.domain.model.User
import com.example.featurehome.domain.repository.EventRepository

class EventRepositoryImpl(private val remoteDataSource: EventRemoteDataSource) : EventRepository {
    override suspend fun getEventList(): Result<List<Event>> {
        return remoteDataSource.getEventList()
    }

    override suspend fun getEventDetails(id: Int): Result<EventDetails> {
        return remoteDataSource.getEventDetails(id)
    }

    override suspend fun doCheckIn(user: User): Result<Unit> {
        return remoteDataSource.doCheckIn(user)
    }
}