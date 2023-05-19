package test.app.data.di

import com.example.network.NetworkRepositoryImpl
import com.example.network.RemoteApi
import com.example.network.Retrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Singleton
    @Provides
    @Named("ApiKey")
    fun apiKey() = "DW0E98NrxUIfDDtNN7ijruVSm60ryFLX"  // TODO add to android keystore

    @Singleton
    @Provides
    fun provideApi( @Named("ApiKey") apikey: String) = Retrofit().provideApi(apikey)

    @Singleton
    @Provides
    fun provideRepository(api: RemoteApi) = NetworkRepositoryImpl(api)

}