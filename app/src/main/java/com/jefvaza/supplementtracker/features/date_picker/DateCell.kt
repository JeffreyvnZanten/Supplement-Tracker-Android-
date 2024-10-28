
package com.jefvaza.supplementtracker.DatePicker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

/**
 * Simple DateCell composable to display a clickable date.
 */
@Composable
fun DateCell(date: kotlinx.datetime.LocalDate, isSelected: Boolean, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable { onClick() }
    ) {
        // Display the date as text
        Text(
            text = date.toString(),
            color = if (isSelected) Color.Red else Color.Black
        )
    }
}
