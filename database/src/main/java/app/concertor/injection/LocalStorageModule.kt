package app.concertor.injection

import app.concertor.AppDatabase
import app.concertor.source.mappers.EventsMapperImpl
import app.concertor.source.*
import dagger.Module
import dagger.Provides

@Module
class LocalStorageModule {

    @Provides
    fun provideEventsDataSource(
            appDatabase: AppDatabase
    ): EventsLocalStore {

        return EventsLocalStoreImpl(appDatabase, EventsMapperImpl())
    }

    @Provides
    fun providePlannedEventsDataSource(appDatabase: AppDatabase): PlannedEventsLocalStore =
            PlannedEventsLocalStoreImpl(appDatabase)

    @Provides
    fun provideArtistsLocalStore(appDatabase: AppDatabase): ArtistsLocalStore =
            ArtistsLocalStoreImpl(appDatabase)
}
