package app.concertor.interactor.events

import app.concertor.UseCase
import app.concertor.models.Event
import app.concertor.repository.ArtistsRepository
import app.concertor.repository.EventsRepository
import javax.inject.Inject

class GetEventsUseCase @Inject constructor(
        private val eventsRepository: EventsRepository,
        private val artistsRepository: ArtistsRepository,
        private val eventsMerger: EventsMerger
) : UseCase<List<Event>, String>() {

    override suspend fun run(params: String): List<Event> {
        return eventsMerger.getMergedEvents(
                events = eventsRepository.getEventsForArtist(artistName = params),
                favoriteArtistsIds = artistsRepository.getFavoriteArtistsIds(),
                plannedEventsIds = eventsRepository.getPlannedEventsIds())
    }
}
