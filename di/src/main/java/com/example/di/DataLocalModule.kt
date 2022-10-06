package com.example.di

import androidx.room.Room
import com.example.datalocal.datasource.EventCacheDataSourceImpl
import org.koin.dsl.module

val dataLocalModule = module {
    single {
        Room.databaseBuilder(
            get(),
            EventCacheDataSourceImpl::class.java, "database-name"
        ).build()
    }

    single {
        get<EventCacheDataSourceImpl>().eventCacheDataSource()
    }
}