package com.jefvaza.supplementtracker;

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplementsDao {
    @Query("SELECT * FROM supplements")
    fun getAllSupplements(): Flow<List<Supplement>>

    @Query("SELECT * FROM supplements WHERE timestamp BETWEEN :start AND :end ORDER BY timestamp DESC")
    fun getSupplementsByDateRange(start: Long, end: Long): Flow<List<Supplement>>

    @Query("SELECT * FROM supplements WHERE date(timestamp/1000, 'unixepoch', 'localtime') = date(:date)")
    fun getSupplementsByDate(date: String): Flow<List<Supplement>?>

    @Upsert
    suspend fun upsertSupplement(supplement: Supplement)
}