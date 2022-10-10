package com.example.featureauth.data.repository

import com.example.datalocal.datasource.EventUserCacheDataSource
import com.example.featureauth.domain.repository.UserRepository

class UserRepositoryImpl(private val eventUserCacheDataSource: EventUserCacheDataSource) :
    UserRepository {
    override fun saveUserEmail(email: String) {
        eventUserCacheDataSource.saveUserEmail(email)
    }

    override fun saveUserName(name: String) {
        eventUserCacheDataSource.saveUserName(name)
    }
}