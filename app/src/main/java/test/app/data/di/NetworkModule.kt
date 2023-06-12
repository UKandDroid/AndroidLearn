package test.app.data.di

import com.example.network.NetworkRepository
import com.example.network.NetworkRepositoryImpl
import com.example.network.RemoteApi
import com.example.network.RetrofitApiBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun provideApi() = RetrofitApiBuilder().provideApi()

    @Singleton
    @Provides
    fun provideRepository(api: RemoteApi):NetworkRepository = NetworkRepositoryImpl(api)

}