package app.concertor.injection

import app.concertor.SongkickApi
import app.concertor.mappers.EventsMapper
import app.concertor.mappers.EventsMapperImpl
import app.concertor.source.EventsRemoteStore
import app.concertor.source.EventsRemoteStoreImpl
import dagger.Module
import dagger.Provides

@Module
class EventsRestModule {

    @Provides
    fun provideEventsMapper(): EventsMapper = EventsMapperImpl()

    @Provides
    fun provideEventsDataSource(
            apiService: SongkickApi,
            mapper: EventsMapper
    ): EventsRemoteStore {

        return EventsRemoteStoreImpl(apiService, mapper)
    }
}
