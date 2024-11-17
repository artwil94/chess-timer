package com.example.chesstimer.composable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.chesstimer.ui.theme.ChessTheme

@Composable
fun TimePill(
    text: String,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(100.dp)
            .background(
                color = if (selected) ChessTheme.colors.timerActivated else Color.LightGray,
                shape = ChessTheme.shapes.timePill
            )
            .clickable {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(
                start = ChessTheme.dimensions.paddingM,
                end = ChessTheme.dimensions.paddingM,
                top = ChessTheme.dimensions.paddingS,
                bottom = ChessTheme.dimensions.paddingS
            ),
            text = text,
            style = if (selected) ChessTheme.typography.timePillSelected
            else ChessTheme.typography.timePill
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
private fun TimePillPreview() {
    TimePill(text = "5:00")
}
