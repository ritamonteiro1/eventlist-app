package com.example.di

import androidx.room.Room
import com.example.datalocal.datasource.EventCacheDataSourceImpl
import com.example.datalocal.datasource.EventUserCacheDataSource
import com.example.datalocal.datasource.EventUserCacheDataSourceImpl
import org.koin.android.ext.koin.androidContext
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

    single<EventUserCacheDataSource> {
        EventUserCacheDataSourceImpl(androidContext())
    }
}