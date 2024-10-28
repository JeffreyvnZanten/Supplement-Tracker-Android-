package com.jefvaza.supplementtracker

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun DateSwitcher(
    currentDate: LocalDate,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {
    val buttonModifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp)

    val displayText = when {
        currentDate.isEqual(LocalDate.now()) -> "Vandaag"
        currentDate.isEqual(LocalDate.now().plusDays(1)) -> "Morgen"
        currentDate.isEqual(LocalDate.now().minusDays(1)) -> "Gisteren"
        else -> currentDate.dayOfWeek.getDisplayName(TextStyle.FULL, Locale("nl"))
    }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onPreviousClick,
            modifier = buttonModifier
        ) {
//            Text("<")
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                contentDescription = ""
            )
        }
        Text(
            text = displayText,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Button(
            onClick = onNextClick,
            modifier = buttonModifier
        ) {
//            Text(">")
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = ""
            )
        }
    }
}