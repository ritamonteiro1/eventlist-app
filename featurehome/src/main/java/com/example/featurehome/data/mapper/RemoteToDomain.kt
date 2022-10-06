package com.example.featurehome.data.mapper

import com.example.featurehome.data.remote.model.EventDetailsResponse
import com.example.featurehome.data.remote.model.EventResponse
import com.example.featurehome.domain.model.Event
import com.example.featurehome.domain.model.EventDetails

fun List<EventResponse>.toDomain(): List<Event> {
    return this.map {
        Event(it.id ?: -1, it.image.orEmpty(), it.title.orEmpty(), it.date ?: -1)
    }
}

fun EventDetailsResponse.toDomain(): EventDetails {
    return EventDetails(
        id ?: -1,
        image.orEmpty(),
        title.orEmpty(),
        description.orEmpty(),
        price ?: -1.0,
        date ?: -1,
    )
}