package com.jefvaza.supplementtracker.features.supplement_list_item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jefvaza.supplementtracker.Supplement

@Composable
fun SupplementListItem(supplement: Supplement) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = Color.LightGray,
        ) {
            Text(
                text = Utils.formatTime(supplement.timestamp),
                fontSize = 16.sp,
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                color = Color.Black
            )
        }

        Text(
            text = supplement.name,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 12.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewSupplementListItem() {
    var supplement = Supplement(name = "Zink", timestamp = System.currentTimeMillis())

    SupplementListItem(supplement)
}