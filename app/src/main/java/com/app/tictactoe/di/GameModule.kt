package com.app.tictactoe.di

import com.app.tictactoe.domain.TicTacToeEngine
import com.app.tictactoe.domain.TicTacToeGame
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class GameModule {

    @Binds
    @Singleton
    abstract fun bindTicTacToeEngine(
        ticTacToeGame: TicTacToeGame
    ): TicTacToeEngine
}