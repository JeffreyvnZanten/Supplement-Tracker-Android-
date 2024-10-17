package com.jefvaza.supplementtracker

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SupplementList(supplementsFlow: Flow<List<Supplement>>) {
    val supplements by supplementsFlow.collectAsState(initial = emptyList())
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    if (supplements.isEmpty()) {
        Text(
            "No supplements for today",
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    } else {
        LazyColumn {
            items(supplements) { supplement ->
                val formattedTime = supplement.timestamp?.let { timestamp ->
                    dateFormat.format(Date(timestamp))
                } ?: "No time"
                Text(
                    text = "${supplement.name} - $formattedTime",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                )
            }
        }
    }
}