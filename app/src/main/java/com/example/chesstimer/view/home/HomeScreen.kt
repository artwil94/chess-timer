package com.example.chesstimer.view.home

import android.annotation.SuppressLint
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
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.chesstimer.composable.components.ConfirmationDialog
import com.example.chesstimer.composable.components.TimeBottomSheet
import com.example.chesstimer.ui.theme.ChessTheme
import kotlinx.coroutines.delay

const val DEFAULT_TIME_PILL_INDEX = 3
const val DEFAULT_TIME = 600

@ExperimentalLayoutApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen() {
    val timesInt = listOf(1, 3, 5, 10, 15, 30, 60, 120)
    val selectedTimeIndex = remember { mutableIntStateOf(DEFAULT_TIME_PILL_INDEX) }
    val selectedTime = remember { mutableIntStateOf(DEFAULT_TIME) }
    val selectedTimeFor2Player = remember { mutableIntStateOf(DEFAULT_TIME) }
    var timeLeftPlayerFirst by remember { mutableIntStateOf(timesInt[selectedTimeIndex.intValue] * 60) }
    var timeLeftPlayerSecond by remember { mutableIntStateOf(timesInt[selectedTimeIndex.intValue] * 60) }
    var isPlayerFirstActive by remember { mutableStateOf(false) }
    var isTimerRunning by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer.create(context, R.raw.clock_switch) }
    var showTimeBottomSheet by remember { mutableStateOf(false) }
    var showConfirmationDialog by remember { mutableStateOf(false) }
    ChangeSystemBarColor(
        statusBarColor = if (isPlayerFirstActive && isTimerRunning) ChessTheme.colors.timerActivated else Color.LightGray,
        navigationBarColor = if (!isPlayerFirstActive && isTimerRunning) ChessTheme.colors.timerActivated else Color.LightGray
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
                .background(if (isPlayerFirstActive && isTimerRunning) ChessTheme.colors.timerActivated else Color.LightGray)
                .weight(1f)
                .graphicsLayer(rotationZ = 180f)
                .clickable(enabled = isPlayerFirstActive) {
                    isPlayerFirstActive = false
                    isTimerRunning = true
                    mediaPlayer.start()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = formatTime(timeLeftPlayerFirst),
                style = if (isPlayerFirstActive && isTimerRunning) ChessTheme.typography.timerActivated else ChessTheme.typography.timer
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(ChessTheme.dimensions.padding),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                modifier = Modifier.clickable {
                    isTimerRunning = false
                    showConfirmationDialog = true
                },
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
                    showTimeBottomSheet = true
                    isTimerRunning = false
                },
                painter = painterResource(id = R.drawable.ic_hourglass),
                contentDescription = stringResource(id = R.string.content_description_change_time),
                tint = Color.White
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(if (!isPlayerFirstActive && isTimerRunning) ChessTheme.colors.timerActivated else Color.LightGray)
                .weight(1f)
                .clickable(enabled = !isPlayerFirstActive) {
                    isPlayerFirstActive = !isPlayerFirstActive
                    isTimerRunning = true
                    mediaPlayer.start()
                },
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = formatTime(timeLeftPlayerSecond),
                style = if (!isPlayerFirstActive && isTimerRunning) ChessTheme.typography.timerActivated else ChessTheme.typography.timer
            )
        }
    }
    if (showTimeBottomSheet) {
        TimeBottomSheet(
            title = stringResource(id = R.string.choose_time),
            times = timesInt,
            onDismiss = { showTimeBottomSheet = false },
            onDone = { time ->
                timeLeftPlayerFirst = timesInt[time] * 60
                timeLeftPlayerSecond = timesInt[time] * 60
                selectedTime.intValue = timesInt[time] * 60
                selectedTimeFor2Player.intValue = timesInt[time] * 60
                showTimeBottomSheet = false
            },
            onChangeTimeForSecondPlayer = { time2 ->
                selectedTimeFor2Player.intValue = timesInt[time2] * 60
                timeLeftPlayerSecond = timesInt[time2] * 60
            }
        )
    }
    if (showConfirmationDialog) {
        ConfirmationDialog(
            title = stringResource(id = R.string.reset_clock),
            confirmText = stringResource(id = R.string.confirm),
            message = stringResource(id = R.string.reset_clock_message),
            cancelText = stringResource(id = R.string.cancel),
            onConfirm = {
                timeLeftPlayerFirst = selectedTime.intValue
                timeLeftPlayerSecond = selectedTimeFor2Player.intValue
                showConfirmationDialog = false
            },
            onCancel = { showConfirmationDialog = false }
        )
    }
}

@SuppressLint("DefaultLocale")
fun formatTime(seconds: Int): String {
    val hours = seconds / 3600
    val minutes = (seconds % 3600) / 60
    val remainingSeconds = seconds % 60
    return if (seconds >= 3600) {
        String.format("%d:%02d:%02d", hours, minutes, remainingSeconds)
    } else {
        String.format("%2d:%02d", minutes, remainingSeconds)
    }
}