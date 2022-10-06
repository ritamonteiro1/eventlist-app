package com.example.featurehome.data.remote.service

import com.example.featurehome.data.remote.model.EventDetailsResponse
import com.example.featurehome.data.remote.model.EventResponse
import com.example.featurehome.data.remote.model.UserRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EventService {
    @GET("events")
    suspend fun getEventList(): List<EventResponse>

    @GET("events/{id}")
    suspend fun getEventDetails(
        @Path("id") id: Int
    ): EventDetailsResponse

    @POST("checkin")
    suspend fun doCheckIn(@Body user: UserRequest)
}