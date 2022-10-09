package com.example.core.utils

import java.text.SimpleDateFormat
import java.util.*

fun Long.toStringDate(): String {
    val formatDate = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR"))
    val date = Date(this)
    return formatDate.format(date)
}