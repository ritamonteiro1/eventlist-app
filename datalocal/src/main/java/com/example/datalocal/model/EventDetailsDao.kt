package com.example.datalocal.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventDetailsDao(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val image: String,
    val title: String,
    val description: String,
    val price: Double,
    val date: Long,
)
