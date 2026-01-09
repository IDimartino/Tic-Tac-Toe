package com.app.tictactoe.domain

interface TicTacToeEngine {
    val currentPlayer: Player
    val result: GameResult
    fun play(position: Int): Boolean
    fun getBoard(): Map<Int, Player>
    fun reset()
}