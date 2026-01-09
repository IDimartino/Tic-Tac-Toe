package com.app.tictactoe.domain

sealed class GameResult {
    data object InProgress : GameResult()
    data class Win(val player: Player) : GameResult()
    data object Draw : GameResult()
}