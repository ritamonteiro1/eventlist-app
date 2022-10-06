package com.example.featurehome.domain.model

data class EventDetails(
    val id: Int,
    val image: String,
    val title: String,
    val description: String,
    val price: Double,
    val date: Long,
)