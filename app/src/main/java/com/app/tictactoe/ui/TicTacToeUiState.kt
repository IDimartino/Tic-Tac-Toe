package com.app.tictactoe.ui

import com.app.tictactoe.domain.GameResult
import com.app.tictactoe.domain.Player
import com.app.tictactoe.ui.util.UiText

data class TicTacToeUiState(
    val board: Map<Int, Player> = emptyMap(),
    val result: GameResult = GameResult.InProgress,
    val statusText: UiText = UiText.DynamicString("") // Added this
)