package com.app.tictactoe.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class BoardTest {

    @Test
    fun `initial board should be empty`() {
        val board = Board()
        assertTrue(board.allCells().isEmpty())
    }

    @Test
    fun `playing on an empty cell should update the board`() {
        val board = Board()
        val newBoard = board.play(0, Player.X)
        
        assertEquals(Player.X, newBoard.getPlayerAt(0))
        assertEquals(1, newBoard.allCells().size)
    }
}
