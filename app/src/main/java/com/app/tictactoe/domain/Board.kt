package com.app.tictactoe.domain

import com.app.tictactoe.domain.GameConstants.CELL_COUNT

data class Board(
    private val cells: Map<Int, Player> = emptyMap()
) {
    fun play(position: Int, player: Player): Board {
        // Use CELL_COUNT for bounds check instead of hardcoded 9
        if (position !in 0 until CELL_COUNT || cells.containsKey(position)) {
            return this
        }
        return copy(cells = cells + (position to player))
    }

    fun getPlayerAt(position: Int): Player? = cells[position]

    fun isFull(): Boolean = cells.size == CELL_COUNT

    fun allCells(): Map<Int, Player> = cells

    fun hasWinner(player: Player): Boolean {
        val positions = cells.filterValues { it == player }.keys
        return winningLines.any { line -> line.all { it in positions } }
    }

    companion object {
        private val winningLines = listOf(
            listOf(0, 1, 2), listOf(3, 4, 5), listOf(6, 7, 8), // Rows
            listOf(0, 3, 6), listOf(1, 4, 7), listOf(2, 5, 8), // Columns
            listOf(0, 4, 8), listOf(2, 4, 6)             // Diagonals
        )
    }
}