package com.example.featurehome.data.remote.datasource

import com.example.core.model.Result
import com.example.featurehome.data.mapper.toDomain
import com.example.featurehome.data.mapper.toRemote
import com.example.featurehome.data.remote.service.EventService
import com.example.featurehome.data.remote.utils.retrofitWrapper
import com.example.featurehome.domain.model.Event
import com.example.featurehome.domain.model.EventDetails
import com.example.featurehome.domain.model.User

class EventRemoteDataSourceImpl(private val service: EventService) : EventRemoteDataSource {
    override suspend fun getEventList(): Result<List<Event>> {
        return when (val result = retrofitWrapper { service.getEventList() }) {
            is Result.Error -> result
            is Result.Success -> Result.Success(result.data.toDomain())
        }
    }

    override suspend fun getEventDetails(id: Int): Result<EventDetails> {
        return when (val result = retrofitWrapper { service.getEventDetails(id) }) {
            is Result.Error -> result
            is Result.Success -> Result.Success(result.data.toDomain())
        }
    }

    override suspend fun doCheckIn(user: User): Result<Unit> {
        return when (val result = retrofitWrapper { service.doCheckIn(user.toRemote()) }) {
            is Result.Error -> result
            is Result.Success -> Result.Success(Unit)
        }
    }
}