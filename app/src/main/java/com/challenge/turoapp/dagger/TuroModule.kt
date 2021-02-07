package com.challenge.turoapp.dagger

import com.challenge.turoapp.repository.Repository
import com.challenge.turoapp.repository.RepositoryImpl
import com.challenge.turoapp.repository.remote.Constants
import com.challenge.turoapp.repository.remote.RemoteSource
import com.challenge.turoapp.repository.remote.RemoteSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TuroModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(): RemoteSource =
        RemoteSourceImpl.create(Constants.BASE_URL)

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteSource): Repository =
        RepositoryImpl(remoteDataSource)
}