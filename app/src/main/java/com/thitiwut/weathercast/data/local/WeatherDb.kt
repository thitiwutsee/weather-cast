package com.thitiwut.weathercast.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocalHistory::class],
    version = 1,
    exportSchema = false
)
abstract class WeatherDb :RoomDatabase(){
    abstract val dao: HistoryDao
}