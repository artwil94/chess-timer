package com.example.chesstimer.composable.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.chesstimer.ui.theme.ChessTheme

@Composable
fun ConfirmationDialog(
    title: String,
    message: String? = null,
    confirmText: String,
    cancelText: String? = null,
    onConfirm: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDismiss: () -> Unit = { if (cancelText == null) onConfirm.invoke() else onCancel.invoke() }
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            dismissOnClickOutside = false
        )
    ) {
        Box(
            modifier = Modifier
                .background(
                    color = Color.White,
                    shape = ChessTheme.shapes.confirmationDialog
                )
        ) {
            Column(
                modifier = Modifier.padding(
                    ChessTheme.dimensions.paddingMedium,
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    style = ChessTheme.typography.confirmationDialogTitle
                )
                message?.let {
                    Spacer(modifier = Modifier.height(ChessTheme.dimensions.padding))
                    Text(
                        text = message,
                        style = ChessTheme.typography.confirmationDialogMessage
                    )
                }
                Spacer(modifier = Modifier.height(ChessTheme.dimensions.paddingL))
                ActionButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = confirmText,
                    onClick = { onConfirm.invoke() },
                    color = ChessTheme.colors.timerActivated
                )
                Spacer(modifier = Modifier.height(ChessTheme.dimensions.paddingM))
                cancelText?.let {
                    ActionButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = cancelText,
                        onClick = { onCancel.invoke() },
                        color = ChessTheme.colors.textPrimary
                    )
                }
            }
        }
    }
}