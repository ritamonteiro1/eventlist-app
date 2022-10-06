package com.example.datalocal.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.datalocal.model.EventDao
import com.example.datalocal.model.EventDetailsDao

@Database(entities = [EventDao::class, EventDetailsDao::class], version = 1)
abstract class EventCacheDataSourceImpl : RoomDatabase() {
    abstract fun eventCacheDataSource(): EventCacheDataSource
}