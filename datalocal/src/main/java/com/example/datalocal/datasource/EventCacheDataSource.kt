package com.example.datalocal.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.datalocal.model.EventDao
import com.example.datalocal.model.EventDetailsDao

@Dao
interface EventCacheDataSource {
    @Query("SELECT * FROM eventDetailsDao WHERE id = :id")
    suspend fun getEventDetails(id: Int): EventDetailsDao?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEventDetails(eventDetails: EventDetailsDao)

    @Query("SELECT * FROM eventDao")
    suspend fun getEventList(): List<EventDao>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEventList(eventList: List<EventDao>)
}