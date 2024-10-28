package com.jefvaza.supplementtracker

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.jefvaza.supplementtracker.features.supplement_list_item.SupplementListItem

@Composable
fun SupplementList(supplements: List<Supplement>) {
    if (supplements.isEmpty()) {
        Text(
            "Er is voor deze dag geen supplement toegevoegd",
            fontSize = 18.sp,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    } else {
        LazyColumn {
            items(supplements) { supplement ->
                SupplementListItem(supplement)
            }
        }
    }
}

@Preview
@Composable
private fun PreviewSupplementList() {
    val supplementsPreview = listOf(
        Supplement(name = "Zink", timestamp = System.currentTimeMillis()),
        Supplement(name = "Magnesium", timestamp = System.currentTimeMillis()),
        Supplement(name = "Vitamine C", timestamp = System.currentTimeMillis()),
        Supplement(name = "Visolie", timestamp = System.currentTimeMillis()),
        Supplement(name = "Ashwaganda", timestamp = System.currentTimeMillis())
    )

    SupplementList(supplements = supplementsPreview)
}