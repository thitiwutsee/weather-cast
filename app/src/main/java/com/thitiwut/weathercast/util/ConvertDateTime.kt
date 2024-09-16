package com.thitiwut.weathercast.util

import android.util.Log
import okhttp3.internal.UTC
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

fun Int.convertUnixToHHmm(): String {
    val datetime = LocalDateTime.ofInstant(Instant.ofEpochSecond(this.toLong()), ZoneId.of(UTC.id))
    return "${datetime.hour}:${datetime.minute}"
}

fun Int.convertUnixToHHmm(timeZone: Int): String {
    val dateTime = LocalDateTime.ofInstant(
        Instant.ofEpochSecond(this.toLong()),
        ZoneOffset.ofTotalSeconds(timeZone)
    )
    return "${if (dateTime.hour.toString().length > 1) "" else "0"}${dateTime.hour}:${if (dateTime.minute.toString().length > 1) "" else "0"}${dateTime.minute}"
}

fun isDaytime(time: Long, timeZone: Int): Boolean {
    val dateTime =
        LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneOffset.ofTotalSeconds(timeZone))
    Log.d("HOUR", "isDaytime: ${dateTime.hour}")
    return dateTime.hour in 6..18
}

fun isDaytime(time: Long): Boolean {
    val dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(time), ZoneId.systemDefault())
    Log.d("HOUR", "isDaytime: ${dateTime.hour}")
    return dateTime.hour in 6..18
}