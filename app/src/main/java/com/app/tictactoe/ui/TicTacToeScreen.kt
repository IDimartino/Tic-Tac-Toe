package com.app.tictactoe.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.app.tictactoe.R
import com.app.tictactoe.domain.Player
import com.app.tictactoe.ui.theme.TicTacToeTheme

@Composable
fun TicTacToeScreen(
    viewModel: TicTacToeViewModel,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
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

        // STABILIZATION: Fixed height Box keeps the Board position constant
        Box(
            modifier = Modifier.height(dimensions.statusContainerHeight),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = uiState.statusText.asString(),
                style = MaterialTheme.typography.headlineSmall
            )
        }

        Spacer(modifier = Modifier.height(dimensions.largePadding))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .aspectRatio(1f)
                .border(dimensions.boardBorderWidth, MaterialTheme.colorScheme.primary)
        ) {
            items(9) { index ->
                CellView(
                    player = uiState.board[index],
                    onClick = { onAction(TicTacToeAction.CellClicked(index)) }
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensions.extraLargePadding))

        Button(
            onClick = { onAction(TicTacToeAction.ResetClicked) },
            modifier = Modifier.height(dimensions.actionButtonHeight)
        ) {
            Text(stringResource(R.string.reset_game))
        }
    }
}

@Composable
fun CellView(player: Player?, onClick: () -> Unit) {
    val dimensions = TicTacToeTheme.dimensions
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .border(dimensions.cellBorderWidth, MaterialTheme.colorScheme.outline)
            .clickable(enabled = player == null, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = player?.name ?: "",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = if (player == Player.X) MaterialTheme.colorScheme.primary else Color.Red
        )
    }
}