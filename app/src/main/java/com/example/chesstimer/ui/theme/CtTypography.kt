package com.example.chesstimer.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

data class CtTypography(
    val inputField: TextStyle = TextStyle(
        fontSize = 16.sp,
        fontFamily = ChessTheme.fonts.robotoLight,
        fontWeight = FontWeight(300),
        color = Color(0xFF333333),
        platformStyle = PlatformTextStyle(
            includeFontPadding = false
        )
    ),
    val timer: TextStyle = TextStyle(
        fontSize = 100.sp,
        lineHeight = 100.sp,
        fontFamily = ChessTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color(0xFF333333),
        letterSpacing = 5.sp
    ),
    val timerActivated: TextStyle = TextStyle(
        fontSize = 100.sp,
        lineHeight = 100.sp,
        fontFamily = ChessTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color.White,
        letterSpacing = 5.sp
    ),
    val timeBottomSheetTitle: TextStyle = TextStyle(
        fontSize = 24.sp,
        lineHeight = 28.sp,
        fontFamily = ChessTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color(0xFF333333),
    ),
    val actionButton: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontFamily = ChessTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color(0xFF333333),
    ),
    val actionButtonWhite: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 22.sp,
        fontFamily = ChessTheme.fonts.freigeistMedium,
        fontWeight = FontWeight(550),
        color = Color.White,
    ),
    val timePill: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight(550),
        color = Color(0xFF333333),
        fontFamily = ChessTheme.fonts.freigeistMedium
    ),
    val timePillSelected: TextStyle = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight(550),
        color = Color.White,
        fontFamily = ChessTheme.fonts.freigeistMedium
    ),
    val confirmationDialogTitle: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 28.sp,
        fontWeight = FontWeight(550),
        color = Color(0xFF333333),
        fontFamily = ChessTheme.fonts.freigeistMedium,
        textAlign = TextAlign.Center
    ),
    val confirmationDialogMessage: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight(300),
        color = Color(0xFF333333),
        fontFamily = ChessTheme.fonts.robotoLight,
        textAlign = TextAlign.Center
    )
)