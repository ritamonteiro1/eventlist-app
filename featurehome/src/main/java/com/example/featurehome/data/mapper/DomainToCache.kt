package com.example.featurehome.data.mapper

import com.example.datalocal.model.EventDao
import com.example.datalocal.model.EventDetailsDao
import com.example.featurehome.domain.model.Event
import com.example.featurehome.domain.model.EventDetails

fun List<Event>.toCache(): List<EventDao> {
    return this.map {
        EventDao(it.id, it.image, it.title, it.date)
    }
}

fun EventDetails.toCache(): EventDetailsDao {
    return EventDetailsDao(
        id,
        image,
        title,
        description,
        price,
        date,
    )
}