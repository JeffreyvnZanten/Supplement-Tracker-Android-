package com.jefvaza.supplementtracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class SupplementViewModel(private val dao: SupplementsDao) : ViewModel() {
    val supplements: StateFlow<List<Supplement>> = dao.getAllSupplements()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    //    fun getSupplementsByDate(date: LocalDate): Flow<List<Supplement>> {
//        val start = date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
//        val end = date.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli() - 1
//        return dao.getSupplementsByDateRange(start, end)
//    }

    fun getSupplementsByDate(date: LocalDate): Flow<List<Supplement>> {
        return dao.getSupplementsByDate(date.toString())
            .map { it ?: emptyList() }
            .catch { emit(emptyList()) }
    }

    fun addSupplement(name: String) {
        viewModelScope.launch {
            dao.upsertSupplement(Supplement(name = name))
        }
    }
}