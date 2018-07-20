package app.concertor.injection

import android.content.Context
import androidx.room.Room
import app.concertor.AppDatabase
import app.concertor.mappers.EventsMapper
import app.concertor.mappers.EventsMapperImpl
import app.concertor.source.LocalEventDataSource
import app.concertor.source.LocalEventDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    companion object {
        private const val DB_NAME = "concertor.db"
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                .build()
    }

    @Provides
    fun provideEventsMapper(): EventsMapper = EventsMapperImpl()

    @Provides
    fun provideEventsDataSource(appDatabase: AppDatabase, mapper: EventsMapper): LocalEventDataSource {
        return LocalEventDataSourceImpl(appDatabase, mapper)
    }
}
