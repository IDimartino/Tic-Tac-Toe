package com.app.tictactoe.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
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
    fun `playing on an occupied cell should not change the board`() {
        val board = Board().play(0, Player.X)
        val newBoard = board.play(0, Player.O)

        assertEquals(board, newBoard)
        assertEquals(Player.X, newBoard.getPlayerAt(0))
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
        assertFalse(board.isFull())

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

        assertTrue(board.hasWinner(Player.X))
        assertFalse(board.hasWinner(Player.O))
    }

    @Test
    fun `hasWinner should detect a column win`() {
        val board = Board()
            .play(0, Player.X)
            .play(3, Player.X)
            .play(6, Player.X)

        assertTrue(board.hasWinner(Player.X))
    }

    @Test
    fun `hasWinner should detect a diagonal win`() {
        val board = Board()
            .play(0, Player.X)
            .play(4, Player.X)
            .play(8, Player.X)

        assertTrue(board.hasWinner(Player.X))
    }
}