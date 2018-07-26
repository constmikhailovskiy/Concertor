package app.concertor.injection

import app.concertor.SongkickApi
import app.concertor.source.EventsRemoteStore
import app.concertor.source.EventsRemoteStoreImpl
import app.concertor.source.mappers.EventsRemoteMapper
import app.concertor.source.mappers.EventsRemoteMapperImpl
import dagger.Module
import dagger.Provides

@Module
class RemoteStorageModule {

    @Provides
    fun provideEventsMapper(): EventsRemoteMapper = EventsRemoteMapperImpl()

    @Provides
    fun provideEventsDataSource(
            apiService: SongkickApi,
            mapper: EventsRemoteMapper
    ): EventsRemoteStore {

        return EventsRemoteStoreImpl(apiService, mapper)
    }
}
