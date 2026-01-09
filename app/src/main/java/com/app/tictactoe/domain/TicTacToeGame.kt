package com.app.tictactoe.domain

import javax.inject.Inject

class TicTacToeGame @Inject constructor() : TicTacToeEngine {
    private var board = Board()

    override var currentPlayer: Player = Player.X
        private set

    override var result: GameResult = GameResult.InProgress
        private set

    override fun play(position: Int): Boolean {
        if (result != GameResult.InProgress) return false

        val newBoard = board.play(position, currentPlayer)
        if (newBoard === board) return false

        board = newBoard

        when {
            board.hasWinner(currentPlayer) -> {
                result = GameResult.Win(currentPlayer)
            }
            board.isFull() -> {
                result = GameResult.Draw
            }
            else -> {
                currentPlayer = currentPlayer.next()
            }
        }
        return true
    }

    override fun getBoard(): Map<Int, Player> = board.allCells()

    override fun reset() {
        board = Board()
        currentPlayer = Player.X
        result = GameResult.InProgress
    }
}