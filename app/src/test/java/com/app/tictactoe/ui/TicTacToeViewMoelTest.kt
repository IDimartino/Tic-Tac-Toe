package com.app.tictactoe.ui

import com.app.tictactoe.domain.Player
import com.app.tictactoe.domain.TicTacToeGame
import org.junit.Assert.assertEquals
import org.junit.Test

class TicTacToeViewModelTest {

    @Test
    fun `initial state should match engine initial state`() {
        val game = TicTacToeGame()
        val viewModel = TicTacToeViewModel(game)

        val state = viewModel.uiState.value
        assertEquals(game.getBoard(), state.board)
        assertEquals(game.result, state.result)
    }

    @Test
    fun `onAction CellClicked should update state`() {
        val game = TicTacToeGame()
        val viewModel = TicTacToeViewModel(game)

        // This will FAIL for now
        viewModel.onAction(TicTacToeAction.CellClicked(0))

        val state = viewModel.uiState.value
        assertEquals(1, state.board.size)
        assertEquals(Player.X, state.board[0])
    }
}