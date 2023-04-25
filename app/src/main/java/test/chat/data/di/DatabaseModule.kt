package test.chat.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import test.chat.data.local.MessageDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideMessageDatabase(@ApplicationContext appContext: Context): MessageDatabase {
        return Room.databaseBuilder(
            context = appContext,
            klass = MessageDatabase::class.java,
            name = "messages_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMessageDao(db: MessageDatabase) = db.getMessageDao()
}