package com.app.tictactoe.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TicTacToeGameTest {

    private lateinit var game: TicTacToeGame

    @Before
    fun setUp() {
        game = TicTacToeGame()
    }

    @Test
    fun `initial game state should be in progress with player X`() {
        assertEquals(Player.X, game.currentPlayer)
        assertEquals(GameResult.InProgress, game.result)
        assertTrue(game.getBoard().isEmpty())
    }

    @Test
    fun `successful move should switch player`() {
        assertTrue(game.play(0))
        assertEquals(Player.O, game.currentPlayer)
        assertEquals(Player.X, game.getBoard()[0])
    }

    @Test
    fun `invalid move should not switch player`() {
        game.play(0)
        assertFalse(game.play(0)) // Same position
        assertEquals(Player.O, game.currentPlayer)
    }

    @Test
    fun `game should end in win`() {

        game.play(0) // X
        game.play(3) // O
        game.play(1) // X
        game.play(4) // O
        game.play(2) // X

        assertTrue(game.result is GameResult.Win)
        assertEquals(Player.X, (game.result as GameResult.Win).player)
    }

    @Test
    fun `game should end in draw`() {
        // X X O
        // O O X
        // X O X
        val moves = listOf(0, 3, 1, 4, 5, 2, 6, 7, 8)
        moves.forEach { game.play(it) }

        assertEquals(GameResult.Draw, game.result)
    }

    @Test
    fun `no moves possible after game ends`() {
        game.play(0) // X
        game.play(3) // O
        game.play(1) // X
        game.play(4) // O
        game.play(2) // X

        assertTrue(game.result is GameResult.Win)
        assertFalse(game.play(5))
    }

    @Test
    fun `reset should clear the game state`() {
        game.play(0)
        game.reset()

        assertEquals(Player.X, game.currentPlayer)
        assertEquals(GameResult.InProgress, game.result)
        assertTrue(game.getBoard().isEmpty())
    }
}