package com.jefvaza.supplementtracker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TopBar(title: String) {
    val jadeGreen = Color(0xFF00A86B)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(jadeGreen)
            .statusBarsPadding()
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Normal
        )
    }
}