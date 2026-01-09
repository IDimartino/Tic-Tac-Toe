package com.app.tictactoe.ui

import androidx.lifecycle.ViewModel
import com.app.tictactoe.R
import com.app.tictactoe.domain.GameResult
import com.app.tictactoe.domain.TicTacToeEngine
import com.app.tictactoe.ui.util.UiText
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TicTacToeViewModel @Inject constructor(
    private val game: TicTacToeEngine
) : ViewModel() {

    private val _uiState = MutableStateFlow(TicTacToeUiState())
    val uiState: StateFlow<TicTacToeUiState> = _uiState.asStateFlow()

    init {
        updateState()
    }

    fun onAction(action: TicTacToeAction) {
        when (action) {
            is TicTacToeAction.CellClicked -> {
                if (game.play(action.position)) {
                    updateState()
                }
            }
            TicTacToeAction.ResetClicked -> {
                game.reset()
                updateState()
            }
        }
    }

    private fun updateState() {
        val result = game.result
        val status = when (result) {
            is GameResult.InProgress -> UiText.StringResource(R.string.player_turn, game.currentPlayer)
            is GameResult.Win -> UiText.StringResource(R.string.player_wins, result.player)
            GameResult.Draw -> UiText.StringResource(R.string.draw)
        }

        _uiState.update {
            it.copy(
                board = game.getBoard(),
                result = result,
                statusText = status
            )
        }
    }
}