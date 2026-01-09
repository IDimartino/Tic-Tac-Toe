package com.app.tictactoe.ui.theme

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimensions(
    val defaultPadding: Dp = 0.dp,
    val smallPadding: Dp = 8.dp,
    val mediumPadding: Dp = 16.dp,
    val largePadding: Dp = 24.dp,
    val extraLargePadding: Dp = 32.dp,
    val boardBorderWidth: Dp = 2.dp,
    val cellBorderWidth: Dp = 1.dp,
    val actionButtonHeight: Dp = 48.dp,
    val statusContainerHeight: Dp = 48.dp
)

val LocalDimensions = compositionLocalOf { Dimensions() }
