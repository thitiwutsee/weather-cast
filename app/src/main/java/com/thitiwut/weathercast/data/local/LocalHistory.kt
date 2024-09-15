package com.thitiwut.weathercast.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class LocalHistory(
    @PrimaryKey
    @ColumnInfo(name = "h_id")
    val id: Int,
    @ColumnInfo(name = "h_search_text")
    val searchText:String,
    @ColumnInfo(name = "h_search_flag")
    val searchFlag: Boolean
)
