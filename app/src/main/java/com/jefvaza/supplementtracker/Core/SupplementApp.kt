package com.jefvaza.supplementtracker

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun SupplementApp(
    supplementViewModel: SupplementViewModel,
) {
    val uiState by supplementViewModel.uiState.collectAsState()
    var showPopup by remember { mutableStateOf(false) }
//    val currentDate by dateSwitcherViewModel.currentDate.collectAsState()

    Scaffold(
        topBar = {
            TopBar(title = "Supplement Tracker")
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showPopup = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            DateSwitcher(
                currentDate = uiState.currentDate,
                onPreviousClick = { supplementViewModel.previousDate() },
                onNextClick = { supplementViewModel.nextDate() }
            )
            SupplementList(uiState.supplements)
        }
    }

    if (showPopup) {
        AddSupplementPopup(
            onDismiss = { showPopup = false },
            onAddSupplement = { name, dateTime ->
                supplementViewModel.addSupplement(
                    name = name,
                    dateTime = dateTime
                )
                showPopup = false
            }
        )
    }
}