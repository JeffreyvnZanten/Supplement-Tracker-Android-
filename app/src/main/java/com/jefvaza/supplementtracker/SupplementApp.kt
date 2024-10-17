package com.jefvaza.supplementtracker

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import java.time.LocalDate

@Composable
fun SupplementApp(viewModel: SupplementViewModel) {
    var showPopup by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar( title = "Supplement Tracker")
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showPopup = true }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            SupplementList(viewModel.getSupplementsByDate(LocalDate.now()))
        }
    }

    if (showPopup) {
        AddSupplementPopup(
            onDismiss = { showPopup = false },
            onAddSupplement = { name ->
                viewModel.addSupplement(name)
            }
        )
    }
}