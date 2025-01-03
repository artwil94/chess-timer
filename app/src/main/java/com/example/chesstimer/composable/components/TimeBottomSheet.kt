package com.example.chesstimer.composable.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chesstimer.R
import com.example.chesstimer.ui.theme.ChessTheme

const val DEFAULT_TIME_PILL_INDEX = 3

@ExperimentalLayoutApi
@ExperimentalMaterialApi
@Composable
fun TimeBottomSheet(
    title: String,
    times: List<Int>,
    onDone: (Int) -> Unit,
    onChangeTimeForSecondPlayer: (Int) -> Unit = {},
    onDismiss: () -> Unit = {},
) {
    val selectedTimeIndex = remember { mutableIntStateOf(DEFAULT_TIME_PILL_INDEX) }
    val selectedTimePlayer2 = remember { mutableIntStateOf(3) }
    var equalTimeForBothPlayers by remember { mutableStateOf(true) }

    BottomSheet(onDismiss = onDismiss) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(ChessTheme.dimensions.padding))
            Text(
                modifier = Modifier.align(Alignment.Start),
                text = title,
                style = ChessTheme.typography.timeBottomSheetTitle
            )
            Spacer(modifier = Modifier.height(ChessTheme.dimensions.paddingL))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = ChessTheme.dimensions.padding),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(id = R.string.equal_time_for_both_players),
                    style = ChessTheme.typography.confirmationDialogMessage
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    modifier = Modifier.clickable {
                        equalTimeForBothPlayers = !equalTimeForBothPlayers
                    },
                    painter = painterResource(id = if (equalTimeForBothPlayers) R.drawable.ic_checkbox_yes else R.drawable.ic_checkbox_no),
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            Spacer(modifier = Modifier.height(ChessTheme.dimensions.paddingXL))
            if (equalTimeForBothPlayers.not()) {
                Text(
                    text = stringResource(id = R.string.player_first),
                    style = ChessTheme.typography.confirmationDialogTitle,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(ChessTheme.dimensions.padding))
            }
            FlowRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(ChessTheme.dimensions.paddingS),
                verticalArrangement = Arrangement.spacedBy(ChessTheme.dimensions.padding),
            ) {
                times.forEachIndexed { index, time ->
                    TimePill(
                        text = stringResource(id = R.string.time_pill_min, time),
                        selected = selectedTimeIndex.intValue == index,
                        onClick = {
                            selectedTimeIndex.intValue = index
                        }
                    )
                }
            }
            if (equalTimeForBothPlayers.not()) {
                Spacer(modifier = Modifier.height(ChessTheme.dimensions.padding))
                Text(
                    text = stringResource(id = R.string.player_second),
                    style = ChessTheme.typography.confirmationDialogTitle,
                    modifier = Modifier.align(Alignment.Start)
                )
                Spacer(modifier = Modifier.height(ChessTheme.dimensions.padding))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(ChessTheme.dimensions.paddingS),
                    verticalArrangement = Arrangement.spacedBy(ChessTheme.dimensions.padding),
                ) {
                    times.forEachIndexed { index, time ->
                        TimePill(
                            text = stringResource(id = R.string.time_pill_min, time),
                            selected = selectedTimePlayer2.intValue == index,
                            onClick = {
                                selectedTimePlayer2.intValue = index
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(100.dp))
            ActionButton(
                text = stringResource(id = R.string.done),
                onClick = {
                    onDone.invoke(selectedTimeIndex.intValue)
                    if (equalTimeForBothPlayers.not()) {
                        onChangeTimeForSecondPlayer.invoke(selectedTimePlayer2.intValue)
                    }
                },
                color = ChessTheme.colors.timerActivated
            )
            Spacer(modifier = Modifier.height(ChessTheme.dimensions.padding))
            ActionButton(
                text = stringResource(id = R.string.cancel),
                onClick = {
                    onDismiss.invoke()
                },
                color = ChessTheme.colors.textPrimary
            )
            Spacer(modifier = Modifier.height(ChessTheme.dimensions.paddingMedium))
        }
    }
}