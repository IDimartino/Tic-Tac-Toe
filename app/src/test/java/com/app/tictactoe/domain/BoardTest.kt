package com.app.tictactoe.domain

import org.junit.Assert
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

    @Test
    fun `playing out of bounds should not change the board`() {
        val board = Board()
        assertEquals(board, board.play(-1, Player.X))
        assertEquals(board, board.play(9, Player.X))
    }

    @Test
    fun `isFull should return true only when 9 cells are filled`() {
        var board = Board()
        Assert.assertFalse(board.isFull()) // This will fail to compile

        for (i in 0..8) {
            board = board.play(i, Player.X)
        }

        assertTrue(board.isFull())
    }

    @Test
    fun `hasWinner should detect a row win`() {
        val board = Board()
            .play(0, Player.X)
            .play(1, Player.X)
            .play(2, Player.X)

        assertTrue(board.hasWinner(Player.X)) // This will fail to compile
        Assert.assertFalse(board.hasWinner(Player.O))
    }
}
