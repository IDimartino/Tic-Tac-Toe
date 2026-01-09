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
    }

    @Test
    fun `successful move should switch player`() {
        // This will FAIL in the current state
        assertTrue(game.play(0))
        assertEquals(Player.O, game.currentPlayer)
    }

    @Test
    fun `game should end in win`() {
        // Simulating X winning on the top row
        game.play(0) // X
        game.play(3) // O
        game.play(1) // X
        game.play(4) // O
        game.play(2) // X

        // This will FAIL
        assertTrue(game.result is GameResult.Win)
        assertEquals(Player.X, (game.result as GameResult.Win).player)
    }
}