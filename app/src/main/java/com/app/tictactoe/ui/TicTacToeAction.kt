package com.app.tictactoe.ui

sealed interface TicTacToeAction {
    data class CellClicked(val position: Int) : TicTacToeAction
    data object ResetClicked : TicTacToeAction
}