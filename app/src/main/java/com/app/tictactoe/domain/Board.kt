package com.app.tictactoe.domain

data class Board(
    private val cells: Map<Int, Player> = emptyMap()
) {
    fun play(position: Int, player: Player): Board {
        // Added out-of-bounds check for extra safety
        if (position !in 0..8 || cells.containsKey(position)) {
            return this
        }
        return copy(cells = cells + (position to player))
    }

    fun getPlayerAt(position: Int): Player? = cells[position]

    fun isFull(): Boolean = cells.size == 9

    fun allCells(): Map<Int, Player> = cells

    fun hasWinner(player: Player): Boolean {
        val positions = cells.filterValues { it == player }.keys
        val winningLines = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // Rows
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // Columns
            listOf(0, 4, 8), listOf(2, 4, 6)             // Diagonals
        )
        return winningLines.any { line -> line.all { it in positions } }
    }
}