package com.jefvaza.supplementtracker

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.time.LocalDate
import java.time.ZoneId

class SupplementRepository(private val supplementDao: SupplementsDao) {
    fun getSupplementsByDate(date: LocalDate): Flow<List<Supplement>> {
        val startOfDay = date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
        val endOfDay = date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli() - 1

        return supplementDao.getSupplementsByDateRange(startOfDay, endOfDay)
            .map { it ?: emptyList() }
            .catch { emit(emptyList()) }
    }

    suspend fun addSupplement(supplement: Supplement) {
        supplementDao.upsertSupplement(supplement)
    }
}