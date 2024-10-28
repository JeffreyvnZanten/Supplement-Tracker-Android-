
package com.jefvaza.supplementtracker.DatePicker

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

/**
 * Simplified Composable that displays a scrollable row calendar.
 * No ViewModel or external state management.
 *
 * @param modifier row modifier.
 * @param maxDays number of days to load on each side of the selected date. Default is 365.
 */
@Composable
fun RowKalendar(
    modifier: Modifier = Modifier,
    maxDays: Int = 365,
    onDateSelected: (LocalDate) -> Unit
) {
    // Internal state to keep track of the selected date
    var selectedDate by remember { mutableStateOf(LocalDate(2024, 1, 1)) }
    val startDate = selectedDate.minus(maxDays / 2, DateTimeUnit.DAY)
    val dates = (0 until maxDays).map { startDate.plus(it, DateTimeUnit.DAY) }
    
    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = maxDays / 2)

    LazyRow(
        modifier = modifier,
        state = scrollState,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(dates) { _, date ->
            DateCell(date = date, isSelected = date == selectedDate, onClick = {
                selectedDate = date
                onDateSelected(date)
            })
        }
    }
}
