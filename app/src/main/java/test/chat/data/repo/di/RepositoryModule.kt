package test.chat.data.repo.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.chat.data.repo.RoomCacheRepositoryImpl
import test.chat.domain.repo.LocalRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideCacheRepository(repository: RoomCacheRepositoryImpl): LocalRepository
}