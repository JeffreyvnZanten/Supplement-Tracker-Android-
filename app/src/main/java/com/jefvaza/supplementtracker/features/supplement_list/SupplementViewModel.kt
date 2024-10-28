package com.jefvaza.supplementtracker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefvaza.supplementtracker.features.supplement_list.SupplementUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

class SupplementViewModel(
    private val repository: SupplementRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(SupplementUiState())
    val uiState: StateFlow<SupplementUiState> = _uiState.asStateFlow()

    init {
        loadSupplementsForDate(_uiState.value.currentDate)
    }

    fun previousDate() {
        val newDate = _uiState.value.currentDate.minusDays(1)
        _uiState.update { it.copy(currentDate = newDate) }
        loadSupplementsForDate(newDate)
    }

    fun nextDate() {
        val newDate = _uiState.value.currentDate.plusDays(1)
        _uiState.update { it.copy(currentDate = newDate) }
        loadSupplementsForDate(newDate)
    }

    private fun loadSupplementsForDate(date: LocalDate) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            repository.getSupplementsByDate(date)
                .catch { exception ->
                    _uiState.update { it.copy(isLoading = false, errorMessage = exception.message) }
                }
                .collectLatest { supplementList ->
                    _uiState.update { it.copy(supplements = supplementList, isLoading = false) }
                }
        }
    }

    fun addSupplement(name: String, dateTime: LocalDateTime) {
        viewModelScope.launch {
            val timestamp = dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
            val supplement = Supplement(name = name, timestamp = timestamp)
            repository.addSupplement(supplement)
            // Reload supplements for the date the supplement was added to
            if (dateTime.toLocalDate() == _uiState.value.currentDate) {
                loadSupplementsForDate(_uiState.value.currentDate)
            }
        }
    }
}
