package test.app.data.di

import android.content.Context
import androidx.room.Room
import com.example.core.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMessageDatabase(@ApplicationContext appContext: Context): RoomDatabase {
        return Room.databaseBuilder(
            context = appContext,
            klass = RoomDatabase::class.java,
            name = "messages_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideMessageDao(db: RoomDatabase) = db.getMessageDao()
}