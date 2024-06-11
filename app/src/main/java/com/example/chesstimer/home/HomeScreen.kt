package com.example.chesstimer.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.chesstimer.R
import com.example.chesstimer.composable.ChangeSystemBarColor
import com.example.chesstimer.ui.theme.ChessTheme
import kotlinx.coroutines.delay

@Composable
fun HomeScreen() {
    var timeLeftPlayer1 by remember { mutableStateOf(300) }
    var timeLeftPlayer2 by remember { mutableStateOf(300) }
    var isPlayer1Active by remember { mutableStateOf(false) }
    var isTimerRunning by remember { mutableStateOf(false) }
    ChangeSystemBarColor(
        statusBarColor = if (isPlayer1Active && isTimerRunning) ChessTheme.ctColors.timerActivated else Color.LightGray,
        navigationBarColor = if (!isPlayer1Active && isTimerRunning) ChessTheme.ctColors.timerActivated else Color.LightGray
    )
    LaunchedEffect(isTimerRunning, isPlayer1Active) {
        if (isTimerRunning) {
            while (true) {
                if (isPlayer1Active) {
                    timeLeftPlayer1 = (timeLeftPlayer1 - 1).coerceAtLeast(0)
                } else {
                    timeLeftPlayer2 = (timeLeftPlayer2 - 1).coerceAtLeast(0)
                }
                delay(1000)
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (isPlayer1Active && isTimerRunning) ChessTheme.ctColors.timerActivated else Color.LightGray)
                .weight(1f)
                .graphicsLayer(rotationZ = 180f)
                .clickable {
                    isPlayer1Active = !isPlayer1Active
                    isTimerRunning = true
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = formatTime(timeLeftPlayer1),
                style = if (isPlayer1Active && isTimerRunning) ChessTheme.ctTypography.timerActivated else ChessTheme.ctTypography.timer
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ChessTheme.ctDimensions.padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_reset),
                contentDescription = "reset",
                tint = Color.White
            )
//            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.clickable {
                    isTimerRunning = !isTimerRunning
                },
                painter = painterResource(id = if (isTimerRunning) R.drawable.ic_pause else R.drawable.ic_play),
                contentDescription = "reset",
                tint = Color.White
            )
//            Spacer(modifier = Modifier.weight(1f))
            Icon(
                modifier = Modifier.clickable {
                },
                painter = painterResource(id = R.drawable.ic_hourglass),
                contentDescription = "reset",
                tint = Color.White
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (!isPlayer1Active && isTimerRunning) ChessTheme.ctColors.timerActivated else Color.LightGray)
                .weight(1f)
                .clickable {
                    isPlayer1Active = !isPlayer1Active
                    isTimerRunning = true
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = formatTime(timeLeftPlayer2),
                style = if (!isPlayer1Active && isTimerRunning) ChessTheme.ctTypography.timerActivated else ChessTheme.ctTypography.timer
            )

        }
    }
}

fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%02d:%02d", minutes, remainingSeconds)
}