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

@Composable
fun TicTacToeScreen(
    viewModel: TicTacToeViewModel,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(R.string.app_name),
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Clean: No logic here, the ViewModel decides WHAT to show via UiText
        Text(
            text = uiState.statusText.asString(),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .aspectRatio(1f)
                .border(2.dp, MaterialTheme.colorScheme.primary)
        ) {
            items(9) { index ->
                CellView(
                    player = uiState.board[index],
                    onClick = { viewModel.onAction(TicTacToeAction.CellClicked(index)) }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(onClick = { viewModel.onAction(TicTacToeAction.ResetClicked) }) {
            Text(stringResource(R.string.reset_game))
        }
    }
}

@Composable
fun CellView(player: Player?, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .border(1.dp, MaterialTheme.colorScheme.outline)
            .clickable(enabled = player == null, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = player?.name ?: "",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = if (player == Player.X) Color.Blue else Color.Red
        )
    }
}