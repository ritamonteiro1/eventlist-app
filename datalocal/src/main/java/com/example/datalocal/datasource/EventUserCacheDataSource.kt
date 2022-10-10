package com.example.datalocal.datasource

import com.example.datalocal.model.User

interface EventUserCacheDataSource {
    fun saveUserEmail(email: String)
    fun saveUserName(name: String)
    fun getUserCredentials(): User
}