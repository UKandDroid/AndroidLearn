package test.app.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.app.domain.repo.LocalRepositoryImpl
import test.app.domain.repo.LocalRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideLocalRepository(repository: LocalRepositoryImpl): LocalRepository
}