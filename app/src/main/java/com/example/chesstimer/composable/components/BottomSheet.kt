package com.example.chesstimer.composable.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.chesstimer.ui.theme.ChessTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    isDraggable: Boolean = true,
    backgroundColor: Color = Color.White,
    content: @Composable () -> Unit
) {
    val modelBottomSheetState =
        rememberModalBottomSheetState(true, confirmValueChange = { isDraggable })
    ModalBottomSheet(
        containerColor = backgroundColor,
        onDismissRequest = { onDismiss() },
        sheetState = modelBottomSheetState,
        sheetGesturesEnabled = isDraggable,
        properties = ModalBottomSheetProperties(shouldDismissOnBackPress = isDraggable),
        dragHandle = { BottomSheetDefaults.DragHandle() },
        content = {
            Column(
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(horizontal = ChessTheme.ctDimensions.paddingL)
                    .then(modifier)
            ) {
                content.invoke()
            }
        }
    )
}