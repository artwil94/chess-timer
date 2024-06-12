package com.example.chesstimer.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.chesstimer.R
import com.example.chesstimer.ui.theme.ChessTheme
import kotlinx.coroutines.launch

@ExperimentalLayoutApi
@ExperimentalMaterialApi
@Composable
fun TimeBottomSheet(
    title: String,
    times: List<Int>,
    onDone: (Int) -> Unit,
    bottomSheetState: ModalBottomSheetState,
) {
    val coroutine = rememberCoroutineScope()
    val selectedTimeIndex = remember { mutableIntStateOf(3) }
    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetShape = ChessTheme.ctShapes.bottomSheet,
        sheetContent = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(
                        start = ChessTheme.ctDimensions.padding,
                        end = ChessTheme.ctDimensions.padding
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(ChessTheme.ctDimensions.paddingS))
                BottomSheetHandle()
                Spacer(modifier = Modifier.height(ChessTheme.ctDimensions.dialogPadding))
                Text(
                    modifier = Modifier.align(Alignment.Start),
                    text = title,
                    style = ChessTheme.ctTypography.timeBottomSheetTitle
                )
                Spacer(modifier = Modifier.height(ChessTheme.ctDimensions.dialogPadding))
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(ChessTheme.ctDimensions.padding),
                    verticalArrangement = Arrangement.spacedBy(ChessTheme.ctDimensions.padding),
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
                Spacer(modifier = Modifier.height(100.dp))
                ActionButton(
                    text = "Done",
                    onClick = {
                        onDone.invoke(selectedTimeIndex.intValue)
                        coroutine.launch {
                            bottomSheetState.hide()
                        }
                    },
                    color = ChessTheme.ctColors.timerActivated
                )
                Spacer(modifier = Modifier.height(ChessTheme.ctDimensions.padding))
                ActionButton(
                    text = "Cancel",
                    onClick = {
                        coroutine.launch {
                            bottomSheetState.hide()
                        }
                    },
                    color = ChessTheme.ctColors.textPrimary
                )
                Spacer(modifier = Modifier.height(ChessTheme.ctDimensions.paddingMedium))
            }
        }
    ) {
    }
}
