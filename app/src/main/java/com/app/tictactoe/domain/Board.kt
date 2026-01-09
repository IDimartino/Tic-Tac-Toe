package com.app.tictactoe.domain


data class Board(
    private val cells: Map<Int, Player> = emptyMap()
) {
    fun play(position: Int, player: Player): Board {
        // Validation: Don't allow playing in an already occupied cell
        if (cells.containsKey(position)) {
            return this
        }
        // Return a new board with the updated cell (Immutability)
        return copy(cells = cells + (position to player))
    }

    fun getPlayerAt(position: Int): Player? = cells[position]

    fun allCells(): Map<Int, Player> = cells
}