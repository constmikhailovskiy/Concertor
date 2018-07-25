package app.concertor.interactor.events

import app.concertor.UseCaseSingle
import app.concertor.models.Event
import app.concertor.repository.ArtistsRepository
import app.concertor.repository.EventsRepository
import io.reactivex.Single
import io.reactivex.functions.Function3
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
        private val eventsRepository: EventsRepository,
        private val artistsRepository: ArtistsRepository,
        private val eventsMerger: EventsMerger
) : UseCaseSingle<List<Event>>() {

    lateinit var artistName: String

    override fun buildUseCaseSingle(): Single<List<Event>> {
        return Single.zip(
                eventsRepository.getEventsForArtist(artistName),
                artistsRepository.getFavoriteArtistsIds(),
                eventsRepository.getPlannedEventsIds(),
                Function3 { events, favoriteArtistsIds, plannedEventsIds ->
                    eventsMerger.getMergedEvents(events, favoriteArtistsIds, plannedEventsIds)
                })
    }

}