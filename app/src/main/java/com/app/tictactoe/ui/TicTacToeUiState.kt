package com.app.tictactoe.ui

import com.app.tictactoe.domain.GameResult
import com.app.tictactoe.domain.Player

data class TicTacToeUiState(
    val board: Map<Int, Player> = emptyMap(),
    val result: GameResult = GameResult.InProgress,
    val currentPlayer: Player = Player.X
)