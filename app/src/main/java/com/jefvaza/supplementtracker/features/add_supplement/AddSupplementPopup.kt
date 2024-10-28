package com.jefvaza.supplementtracker

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import java.time.LocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSupplementPopup(
    onDismiss: () -> Unit,
    onAddSupplement: (String, LocalDateTime) -> Unit
) {
    var supplementName by remember { mutableStateOf("") }
    var selectedDateTime by remember { mutableStateOf(LocalDateTime.now()) }

    BasicAlertDialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Card(
            modifier = Modifier
                .width(300.dp)
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text("Add New Supplement", style = MaterialTheme.typography.titleMedium)

                OutlinedTextField(
                    value = supplementName,
                    onValueChange = { supplementName = it },
                    label = { Text("Supplement Name") },
                    modifier = Modifier.fillMaxWidth()
                )

                DateTimeSelector(
                    selectedDateTime = selectedDateTime,
                    onDateTimeSelected = { selectedDateTime = it }
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Button(
                        onClick = {
                            if (supplementName.isNotBlank()) {
                                onAddSupplement(supplementName, selectedDateTime)
                                onDismiss()
                            }
                        }
                    ) {
                        Text("Add")
                    }
                }
            }
        }
    }
}