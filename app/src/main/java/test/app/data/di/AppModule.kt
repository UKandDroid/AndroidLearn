package test.app.data.di

import android.content.Context
import androidx.room.Room
import com.example.core.room.LocalCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import test.app.domain.util.StringRes
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMessageDatabase(@ApplicationContext appContext: Context): LocalCache {
        return Room.databaseBuilder(
            context = appContext,
            klass = LocalCache::class.java,
            name = "messages_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMessageDao(db: LocalCache) = db.getMessageDao()


    @Provides
    @Singleton
    fun provideStringRepo(@ApplicationContext context: Context) = StringRes(context)
}