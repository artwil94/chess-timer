package com.example.chesstimer.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class Shapes(
    val bottomSheet: Shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp),
    val timePill: Shape = RoundedCornerShape(size = 10.dp),
    val confirmationDialog: Shape = timePill
)
