package com.example.featurehome.data.mapper

import com.example.featurehome.data.remote.model.UserRequest
import com.example.featurehome.domain.model.User

fun User.toRemote(): UserRequest {
    return UserRequest(name, email)
}