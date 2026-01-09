package com.app.tictactoe.domain

data class Board(
    private val cells: Map<Int, Player> = emptyMap()
) {
    fun play(position: Int, player: Player): Board {
        // Red phase: Logic not implemented yet, returning this will fail tests
        return this
    }

    fun getPlayerAt(position: Int): Player? {
        return null
    }

    fun allCells(): Map<Int, Player> {
        return emptyMap()
    }
}