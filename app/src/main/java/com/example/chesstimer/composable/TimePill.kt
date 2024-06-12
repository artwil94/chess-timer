package com.example.chesstimer.composable

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

@Preview(showBackground = true, backgroundColor = 0xFFFFFF)
@Composable
fun TimePillPreview() {
    TimePill(text = "5:00")
}

@Composable
fun TimePill(
    text: String,
    selected: Boolean = false,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .width(80.dp)
            .background(
                color = if (selected) ChessTheme.ctColors.timerActivated else Color.LightGray,
                shape = ChessTheme.ctShapes.timePill
            )
            .clickable {
                onClick.invoke()
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.padding(
                start = ChessTheme.ctDimensions.padding,
                end = ChessTheme.ctDimensions.padding,
                top = ChessTheme.ctDimensions.paddingS,
                bottom = ChessTheme.ctDimensions.paddingS
            ),
            text = text,
            style = if (selected) ChessTheme.ctTypography.timePillSelected
            else ChessTheme.ctTypography.timePill
        )
    }
}