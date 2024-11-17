package com.example.chesstimer.composable.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.chesstimer.ui.theme.ChessTheme

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String,
    color: Color = ChessTheme.colors.actionButton,
    @DrawableRes leadingIcon: Int? = null,
    onClick: () -> Unit
) {
    Button(
        onClick = { onClick.invoke() },
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .then(modifier),
        colors = ButtonDefaults.buttonColors(color),
        contentPadding = PaddingValues(
            start = 16.dp,
            top = 8.dp,
            end = 16.dp,
            bottom = 8.dp
        ),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            if (leadingIcon != null) {
                Icon(
                    painter = painterResource(id = leadingIcon),
                    contentDescription = "",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(ChessTheme.dimensions.paddingXs))
            }
            Text(
                text = text.uppercase(),
                style =  ChessTheme.typography.actionButtonWhite
            )
        }
    }
}