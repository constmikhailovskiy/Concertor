package app.concertor.injection

import app.concertor.RetryHandler
import app.concertor.repo.events.EventsRepositoryImpl
import app.concertor.repository.EventsRepository
import app.concertor.source.EventsLocalStore
import app.concertor.source.EventsRemoteStore
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
            handler: RetryHandler
    ): EventsRepository = EventsRepositoryImpl(localStore, remoteStore, handler)
}
