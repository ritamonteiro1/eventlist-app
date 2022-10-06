package com.example.featurehome.data.mapper

import com.example.datalocal.model.EventDao
import com.example.datalocal.model.EventDetailsDao
import com.example.featurehome.domain.model.Event
import com.example.featurehome.domain.model.EventDetails

fun List<EventDao>?.toDomain(): List<Event> {
    return this?.map {
        Event(it.id, it.image, it.title, it.date)
    } ?: emptyList()
}

fun EventDetailsDao.toDomain(): EventDetails {
    return EventDetails(
        id,
        image,
        title,
        description,
        price,
        date,
    )
}