package com.app.tictactoe.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.tictactoe.R
import com.app.tictactoe.domain.GameConstants.BOARD_SIZE
import com.app.tictactoe.domain.GameConstants.CELL_COUNT
import com.app.tictactoe.domain.GameResult
import com.app.tictactoe.domain.Player
import com.app.tictactoe.ui.theme.TicTacToeTheme
import com.app.tictactoe.ui.util.UiText

@Composable
fun TicTacToeScreen(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    viewModel: TicTacToeViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    TicTacToeContent(
        uiState = uiState,
        onAction = viewModel::onAction,
        modifier = modifier,
        contentPadding = contentPadding
    )
}

@Composable
fun TicTacToeContent(
    uiState: TicTacToeUiState,
    onAction: (TicTacToeAction) -> Unit,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val dimensions = TicTacToeTheme.dimensions

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(dimensions.mediumPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(dimensions.extraLargePadding))

        // Fixed height box for status to prevent vertical jumping
        Box(
            modifier = Modifier.height(dimensions.statusContainerHeight),
            contentAlignment = Alignment.Center
        ) {
            AnimatedContent(
                targetState = uiState.statusText,
                transitionSpec = {
                    (fadeIn() + scaleIn()).togetherWith(fadeOut() + scaleOut())
                },
                label = "StatusAnimation"
            ) { targetStatus ->
                Text(
                    text = targetStatus.asString(),
                    style = MaterialTheme.typography.headlineSmall,
                    color = if (uiState.result is GameResult.Win) Color.Green else MaterialTheme.colorScheme.onSurface
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensions.largePadding))

        LazyVerticalGrid(
            columns = GridCells.Fixed(BOARD_SIZE),
            modifier = Modifier
                .aspectRatio(1f)
                .border(dimensions.boardBorderWidth, MaterialTheme.colorScheme.primary)
        ) {
            items(CELL_COUNT) { index ->
                val player = uiState.board[index]
                CellView(
                    player = player,
                    onClick = { onAction(TicTacToeAction.CellClicked(index)) },
                    index = index
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensions.extraLargePadding))

        // Single Reset Button with stable positioning
        Button(
            onClick = { onAction(TicTacToeAction.ResetClicked) },
            modifier = Modifier.height(dimensions.actionButtonHeight)
        ) {
            Text(stringResource(R.string.reset_game))
        }
    }
}

@Composable
fun CellView(
    player: Player?,
    onClick: () -> Unit,
    index: Int
) {
    val dimensions = TicTacToeTheme.dimensions
    val contentDesc = when (player) {
        Player.X -> "Position $index, Player X"
        Player.O -> "Position $index, Player O"
        null -> "Position $index, Empty"
    }

    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .border(dimensions.cellBorderWidth, MaterialTheme.colorScheme.outline)
            .semantics {
                contentDescription = contentDesc
                role = Role.Button
            }
            .clickable(enabled = player == null, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        AnimatedContent(
            targetState = player,
            transitionSpec = {
                scaleIn().togetherWith(fadeOut())
            },
            label = "CellAnimation"
        ) { targetPlayer ->
            Text(
                text = targetPlayer?.name ?: "",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = when (targetPlayer) {
                    Player.X -> MaterialTheme.colorScheme.primary
                    Player.O -> Color.Red
                    null -> Color.Unspecified
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TicTacToePreview() {
    TicTacToeTheme {
        TicTacToeContent(
            uiState = TicTacToeUiState(
                board = mapOf(0 to Player.X, 4 to Player.O),
                statusText = UiText.DynamicString("Player X's Turn")
            ),
            onAction = {}
        )
    }
}