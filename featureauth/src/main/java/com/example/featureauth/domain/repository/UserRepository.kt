package com.example.featureauth.domain.repository

interface UserRepository {
    fun saveUserEmail(email: String)
    fun saveUserName(name: String)
}