package app.concertor.injection

import app.concertor.RetryHandler
import app.concertor.repo.artists.ArtistsRepositoryImpl
import app.concertor.repo.events.EventsRepositoryImpl
import app.concertor.repository.ArtistsRepository
import app.concertor.repository.EventsRepository
import app.concertor.source.ArtistsLocalStore
import app.concertor.source.EventsLocalStore
import app.concertor.source.EventsRemoteStore
import app.concertor.source.PlannedEventsLocalStore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    fun provideRetryHandler() = RetryHandler()

    @Singleton
    @Provides
    fun provideEventsRepository(
            localStore: EventsLocalStore,
            remoteStore: EventsRemoteStore,
            plannedEventsLocalStore: PlannedEventsLocalStore
    ): EventsRepository = EventsRepositoryImpl(localStore, remoteStore, plannedEventsLocalStore)

    @Singleton
    @Provides
    fun provideArtistsRepository(localStore: ArtistsLocalStore): ArtistsRepository {
        return ArtistsRepositoryImpl(localStore)
    }
}
