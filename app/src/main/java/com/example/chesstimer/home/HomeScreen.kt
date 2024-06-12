package com.example.chesstimer.home

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.chesstimer.R
import com.example.chesstimer.composable.ChangeSystemBarColor
import com.example.chesstimer.composable.TimeBottomSheet
import com.example.chesstimer.ui.theme.ChessTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val DEFAULT_TIME_PILL_INDEX = 3

@ExperimentalLayoutApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen() {
    val coroutine = rememberCoroutineScope()
    val timesInt = listOf(1, 3, 5, 10, 15, 30, 60, 120)
    val selectedTimeIndex = remember { mutableIntStateOf(DEFAULT_TIME_PILL_INDEX) }
    var timeLeftPlayerFirst  by remember { mutableIntStateOf(timesInt[selectedTimeIndex.intValue] * 60) }
    var timeLeftPlayerSecond by remember { mutableIntStateOf(timesInt[selectedTimeIndex.intValue] * 60) }
    var isPlayerFirstActive by remember { mutableStateOf(false) }
    var isTimerRunning by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.clock_switch) }
    val timeBottomSheet = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    ChangeSystemBarColor(
        statusBarColor = if (isPlayerFirstActive && isTimerRunning) ChessTheme.ctColors.timerActivated else Color.LightGray,
        navigationBarColor = if (!isPlayerFirstActive && isTimerRunning) ChessTheme.ctColors.timerActivated else Color.LightGray
    )
    LaunchedEffect(isTimerRunning, isPlayerFirstActive) {
        if (isTimerRunning) {
            while (true) {
                if (isPlayerFirstActive) {
                    timeLeftPlayerFirst = (timeLeftPlayerFirst - 1).coerceAtLeast(0)
                } else {
                    timeLeftPlayerSecond = (timeLeftPlayerSecond - 1).coerceAtLeast(0)
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
                .background(if (isPlayerFirstActive && isTimerRunning) ChessTheme.ctColors.timerActivated else Color.LightGray)
                .weight(1f)
                .graphicsLayer(rotationZ = 180f)
                .clickable {
                    isPlayerFirstActive = false
                    isTimerRunning = true
                    mediaPlayer.start()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = formatTime(timeLeftPlayerFirst),
                style = if (isPlayerFirstActive && isTimerRunning) ChessTheme.ctTypography.timerActivated else ChessTheme.ctTypography.timer
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
                contentDescription = stringResource(id = R.string.content_description_reset),
                tint = Color.White
            )
            Icon(
                modifier = Modifier.clickable {
                    isTimerRunning = !isTimerRunning
                },
                painter = painterResource(id = if (isTimerRunning) R.drawable.ic_pause else R.drawable.ic_play),
                contentDescription = if (isTimerRunning) stringResource(id = R.string.content_description_pause)
                else stringResource(id = R.string.content_description_play),
                tint = Color.White
            )
            Icon(
                modifier = Modifier.clickable {
                    coroutine.launch {
                        timeBottomSheet.show()
                    }
                },
                painter = painterResource(id = R.drawable.ic_hourglass),
                contentDescription = "reset",
                tint = Color.White
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (!isPlayerFirstActive && isTimerRunning) ChessTheme.ctColors.timerActivated else Color.LightGray)
                .weight(1f)
                .clickable {
                    isPlayerFirstActive = !isPlayerFirstActive
                    isTimerRunning = true
                    mediaPlayer.start()
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = formatTime(timeLeftPlayerSecond),
                style = if (!isPlayerFirstActive && isTimerRunning) ChessTheme.ctTypography.timerActivated else ChessTheme.ctTypography.timer
            )
        }
    }
    TimeBottomSheet(
        title = "Choose time",
        times = timesInt,
        bottomSheetState = timeBottomSheet,
        onDone = { time ->
            timeLeftPlayerFirst = timesInt[time] * 60
            timeLeftPlayerSecond = timesInt[time] * 60
        }
    )
}

fun formatTime(seconds: Int): String {
    val minutes = seconds / 60
    val remainingSeconds = seconds % 60
    return String.format("%2d:%02d", minutes, remainingSeconds)
}