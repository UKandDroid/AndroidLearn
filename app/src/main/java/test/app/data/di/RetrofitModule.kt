package test.app.data.di

import com.example.network.MainRepositoryImpl
import com.example.network.RemoteApi
import com.example.network.Retrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    fun provideApi() = Retrofit().provideApi()

    @Singleton
    @Provides
    fun provideRepository(api: RemoteApi) = MainRepositoryImpl(api)

}