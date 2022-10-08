package com.example.featurehome.data.repository

import com.example.core.model.NullCacheException
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
        return if (result is Result.Success) {
            val eventListResponse = result.data
            eventCacheDataSource.saveEventList(eventListResponse.toCache())
            getEventListFromCacheAfterSaving()
        } else {
            return getEventListFromCache(result)
        }
    }

    override suspend fun getEventDetails(id: Int): Result<EventDetails> {
        val result = remoteDataSource.getEventDetails(id)
        return if (result is Result.Success) {
            val eventDetailsResponse = result.data
            eventCacheDataSource.saveEventDetails(eventDetailsResponse.toCache())
            getEventDetailsFromCacheAfterSaving(id)
        } else getEventDetailsFromCache(id, result)
    }

    override suspend fun doCheckIn(eventId: Int): Result<Unit> {
        return remoteDataSource.doCheckIn(eventId)
    }

    private suspend fun getEventListFromCache(result: Result<List<Event>>): Result<List<Event>> {
        val eventList = eventCacheDataSource.getEventList()
        if (eventList?.isEmpty() == true) {
            return result
        }
        return Result.Success(eventList.toDomain())
    }

    private suspend fun getEventListFromCacheAfterSaving(): Result<List<Event>> {
        val eventList = eventCacheDataSource.getEventList()
        if (eventList?.isEmpty() == true) {
            return Result.Error(NullCacheException())
        }
        return Result.Success(eventList.toDomain())
    }

    private suspend fun getEventDetailsFromCacheAfterSaving(id: Int): Result<EventDetails> {
        val eventDetailsCache = eventCacheDataSource.getEventDetails(id)
        return if (eventDetailsCache == null) Result.Error(NullCacheException())
        else Result.Success(eventDetailsCache.toDomain())
    }

    private suspend fun getEventDetailsFromCache(
        id: Int,
        result: Result<EventDetails>
    ): Result<EventDetails> {
        val eventDetailsCache = eventCacheDataSource.getEventDetails(id)
        return if (eventDetailsCache == null) result
        else Result.Success(eventDetailsCache.toDomain())
    }
}