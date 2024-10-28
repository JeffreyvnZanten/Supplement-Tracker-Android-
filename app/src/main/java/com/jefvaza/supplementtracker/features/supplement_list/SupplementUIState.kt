package com.jefvaza.supplementtracker.features.supplement_list

import com.jefvaza.supplementtracker.Supplement
import java.time.LocalDate

data class SupplementUiState(
    val currentDate: LocalDate = LocalDate.now(),
    val supplements: List<Supplement> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)
