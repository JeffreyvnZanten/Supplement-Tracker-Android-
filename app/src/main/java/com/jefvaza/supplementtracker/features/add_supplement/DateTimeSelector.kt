package com.jefvaza.supplementtracker

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jefvaza.supplementtracker.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimeSelector(
    selectedDateTime: LocalDateTime,
    onDateTimeSelected: (LocalDateTime) -> Unit
) {
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    // Formatter voor korte tijdweergave
    val timeFormatter = remember { DateTimeFormatter.ofPattern("HH:mm") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedButton(
            onClick = { showDatePicker = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Datum")
            Spacer(Modifier.width(8.dp))
            Text(selectedDateTime.toLocalDate().toString())
        }

        OutlinedButton(
            onClick = { showTimePicker = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Tijd")
            Spacer(Modifier.width(8.dp))
            Text(selectedDateTime.format(timeFormatter))  // Gebruik timeFormatter hier
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            selectedDateTime = selectedDateTime,
            onDateSelected = { date ->
                onDateTimeSelected(LocalDateTime.of(date, selectedDateTime.toLocalTime()))
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false }
        )
    }

    if (showTimePicker) {
        TimePickerDialog(
            selectedDateTime = selectedDateTime,
            onTimeSelected = { time ->
                onDateTimeSelected(LocalDateTime.of(selectedDateTime.toLocalDate(), time))
                showTimePicker = false
            },
            onDismiss = { showTimePicker = false }
        )
    }
}