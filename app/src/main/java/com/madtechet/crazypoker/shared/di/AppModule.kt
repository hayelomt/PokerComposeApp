package com.madtechet.crazypoker.shared.di

import com.madtechet.crazypoker.shared.network.SocketHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.Socket
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSocket(): Socket = SocketHandler.getSocket()

}