package com.thitiwut.weathercast.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HistoryDao {

    @Query("SELECT * FROM history ORDER BY h_id DESC LIMIT 5")
    suspend fun getAll(): List<LocalHistory>

    @Insert
    suspend fun add(history: LocalHistory)

    @Query("SELECT * FROM history WHERE h_search_text LIKE :searchText")
    suspend fun getHistoryByText(searchText: String): LocalHistory

    @Query("SELECT MAX(h_id) FROM history")
    suspend fun getMaxId(): Int

}