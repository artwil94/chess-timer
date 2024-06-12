package com.example.chesstimer.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ColumnScope.BottomSheetHandle() {
    Box(
        modifier = Modifier
            .padding(0.dp)
            .width(36.dp)
            .height(5.dp)
            .background(
                color = Color.Gray,
                shape = RoundedCornerShape(size = 2.5.dp)
            )
            .align(Alignment.CenterHorizontally)
    )
}