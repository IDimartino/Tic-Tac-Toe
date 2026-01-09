package com.app.tictactoe.ui

import androidx.lifecycle.ViewModel
import com.app.tictactoe.domain.TicTacToeEngine
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
        _uiState.update {
            it.copy(
                board = game.getBoard(),
                result = game.result,
                currentPlayer = game.currentPlayer
            )
        }
    }
}