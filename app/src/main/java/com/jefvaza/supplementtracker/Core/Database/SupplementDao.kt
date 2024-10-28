package com.jefvaza.supplementtracker

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplementsDao {
    @Query("SELECT * FROM supplements")
    fun getAllSupplements(): Flow<List<Supplement>>

    @Query("SELECT * FROM supplements WHERE timestamp >= :startTime AND timestamp <= :endTime ORDER BY timestamp ASC")
    fun getSupplementsByDateRange(startTime: Long, endTime: Long): Flow<List<Supplement>>

    @Query("SELECT * FROM supplements WHERE date(timestamp/1000, 'unixepoch', 'localtime') = date(:date)")
    fun getSupplementsByDate(date: String): Flow<List<Supplement>?>

    @Upsert
    suspend fun upsertSupplement(supplement: Supplement)
}

