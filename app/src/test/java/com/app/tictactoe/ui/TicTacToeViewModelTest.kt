package com.app.tictactoe.ui

import com.app.tictactoe.R
import com.app.tictactoe.domain.GameResult
import com.app.tictactoe.domain.Player
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
    fun `onAction CellClicked should update engine and uiState`() {
        viewModel.onAction(TicTacToeAction.CellClicked(0))

        val state = viewModel.uiState.value
        assertEquals(1, state.board.size)
        assertEquals(Player.X, state.board[0])
        assertEquals(GameResult.InProgress, state.result)

        // Status should update to Player O's turn
        assertTrue(state.statusText is UiText.StringResource)
        val status = state.statusText as UiText.StringResource
        assertEquals(R.string.player_turn, status.resId)
        assertEquals(Player.O, status.args[0])
    }

    @Test
    fun `winning move should update uiState with win result and status`() {
        // X: 0, 1, 2
        // O: 3, 4
        viewModel.onAction(TicTacToeAction.CellClicked(0)) // X
        viewModel.onAction(TicTacToeAction.CellClicked(3)) // O
        viewModel.onAction(TicTacToeAction.CellClicked(1)) // X
        viewModel.onAction(TicTacToeAction.CellClicked(4)) // O
        viewModel.onAction(TicTacToeAction.CellClicked(2)) // X

        val state = viewModel.uiState.value
        assertTrue(state.result is GameResult.Win)
        assertEquals(Player.X, (state.result as GameResult.Win).player)

        assertTrue(state.statusText is UiText.StringResource)
        val status = state.statusText as UiText.StringResource
        assertEquals(R.string.player_wins, status.resId)
        assertEquals(Player.X, status.args[0])
    }

    @Test
    fun `draw game should update uiState with draw result and status`() {

        val moves = listOf(0, 3, 1, 4, 5, 2, 6, 7, 8)
        moves.forEach { viewModel.onAction(TicTacToeAction.CellClicked(it)) }

        val state = viewModel.uiState.value
        assertEquals(GameResult.Draw, state.result)

        assertTrue(state.statusText is UiText.StringResource)
        assertEquals(R.string.draw, (state.statusText as UiText.StringResource).resId)
    }

    @Test
    fun `reset action should clear state via engine`() {
        viewModel.onAction(TicTacToeAction.CellClicked(0))
        viewModel.onAction(TicTacToeAction.ResetClicked)

        val state = viewModel.uiState.value
        assertTrue(state.board.isEmpty())
        assertEquals(GameResult.InProgress, state.result)
        assertEquals(R.string.player_turn, (state.statusText as UiText.StringResource).resId)
    }
}