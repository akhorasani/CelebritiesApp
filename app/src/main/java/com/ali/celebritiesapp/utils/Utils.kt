package com.ali.celebritiesapp.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun calculateCurrentDate(pattern: String): String {
    return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern))
}

@RequiresApi(Build.VERSION_CODES.O)
fun calculateFutureDate(pattern: String): String {
    val currentDate = LocalDate.now()
    val futureDate = currentDate.plusDays(13)
    return futureDate.format(DateTimeFormatter.ofPattern(pattern))
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatMyDateTime(dateTimeString: String): String {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val dateTime = LocalDateTime.parse(dateTimeString)
    return dateTime.format(formatter)
}