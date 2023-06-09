package test.app.data.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import test.app.data.StringRes
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StringModule {

    @Provides
    @Singleton
     fun provideStringRepo(@ApplicationContext context: Context) = StringRes(context)
}