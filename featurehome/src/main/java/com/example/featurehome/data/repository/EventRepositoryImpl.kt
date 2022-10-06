package com.example.featurehome.data.repository

import com.example.core.model.GenericErrorException
import com.example.core.model.Result
import com.example.datalocal.datasource.EventCacheDataSource
import com.example.featurehome.data.mapper.toCache
import com.example.featurehome.data.mapper.toDomain
import com.example.featurehome.data.remote.datasource.EventRemoteDataSource
import com.example.featurehome.domain.model.Event
import com.example.featurehome.domain.model.EventDetails
import com.example.featurehome.domain.repository.EventRepository

class EventRepositoryImpl(
    private val remoteDataSource: EventRemoteDataSource,
    private val eventCacheDataSource: EventCacheDataSource
) : EventRepository {
    override suspend fun getEventList(): Result<List<Event>> {
        val result = remoteDataSource.getEventList()
        if (result is Result.Success) {
            val eventList = result.data
            eventCacheDataSource.saveEventList(eventList.toCache())
            return getEventListFromCache()
        }
        return getEventListFromCache()
    }

    override suspend fun getEventDetails(id: Int): Result<EventDetails> {
        val result = remoteDataSource.getEventDetails(id)
        if (result is Result.Success) {
            val eventDetails = result.data
            eventCacheDataSource.saveEventDetails(eventDetails.toCache())
            return getEventDetailsFromCache(id)
        }
        return getEventDetailsFromCache(id)
    }

    override suspend fun doCheckIn(eventId: Int): Result<Unit> {
        return remoteDataSource.doCheckIn(eventId)
    }

    private suspend fun getEventListFromCache(): Result<List<Event>> {
        val eventList = eventCacheDataSource.getEventList()
        if (eventList?.isEmpty() == true) {
            return Result.Error(GenericErrorException())
        }
        return Result.Success(eventList.toDomain())
    }

    private suspend fun getEventDetailsFromCache(id: Int): Result<EventDetails> {
        val eventDetails = eventCacheDataSource.getEventDetails(id)
            ?: return Result.Error(GenericErrorException())
        return Result.Success(eventDetails.toDomain())
    }
}