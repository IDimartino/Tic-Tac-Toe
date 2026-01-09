package com.app.tictactoe.domain

import javax.inject.Inject

class TicTacToeGame @Inject constructor() : TicTacToeEngine {
    override var currentPlayer: Player = Player.X
        private set

    override var result: GameResult = GameResult.InProgress
        private set

    override fun play(position: Int): Boolean {
        // RED phase: No logic yet
        return false
    }

    override fun getBoard(): Map<Int, Player> = emptyMap()

    override fun reset() {
        // No logic yet
    }
}