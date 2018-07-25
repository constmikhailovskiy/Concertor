package app.concertor

import app.concertor.interactor.events.EventsMerger
import app.concertor.interactor.events.EventsMergerImpl
import app.concertor.repository.ArtistsRepository
import app.concertor.repository.EventsRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideEventsMerger(): EventsMerger = EventsMergerImpl()
}