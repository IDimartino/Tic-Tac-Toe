package com.app.tictactoe.ui

import com.app.tictactoe.R
import com.app.tictactoe.domain.GameResult
import com.app.tictactoe.domain.TicTacToeGame
import com.app.tictactoe.ui.util.UiText
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TicTacToeViewModelTest {

    private lateinit var game: TicTacToeGame
    private lateinit var viewModel: TicTacToeViewModel

    @Before
    fun setUp() {
        game = TicTacToeGame()
        viewModel = TicTacToeViewModel(game)
    }

    @Test
    fun `initial state should have player turn status`() {
        val state = viewModel.uiState.value
        assertTrue(state.statusText is UiText.StringResource)
        assertEquals(R.string.player_turn, (state.statusText as UiText.StringResource).resId)
    }

    @Test
    fun `winning move should update status to player wins`() {
        // X: 0, 1, 2
        // O: 3, 4
        viewModel.onAction(TicTacToeAction.CellClicked(0)) // X
        viewModel.onAction(TicTacToeAction.CellClicked(3)) // O
        viewModel.onAction(TicTacToeAction.CellClicked(1)) // X
        viewModel.onAction(TicTacToeAction.CellClicked(4)) // O
        viewModel.onAction(TicTacToeAction.CellClicked(2)) // X

        val state = viewModel.uiState.value
        assertTrue(state.result is GameResult.Win)
        assertTrue(state.statusText is UiText.StringResource)
        assertEquals(R.string.player_wins, (state.statusText as UiText.StringResource).resId)
    }

    @Test
    fun `reset action should clear state`() {
        viewModel.onAction(TicTacToeAction.CellClicked(0))
        viewModel.onAction(TicTacToeAction.ResetClicked)

        val state = viewModel.uiState.value
        assertTrue(state.board.isEmpty())
        assertEquals(GameResult.InProgress, state.result)
    }
}