package app.concertor.interactor.events

import app.concertor.models.Event
import app.concertor.repository.ArtistsRepository
import app.concertor.repository.EventsRepository
import app.concertor.repository.models.EventEntry
import kotlinx.coroutines.experimental.channels.BroadcastChannel
import javax.inject.Inject

class EventsInteractor @Inject constructor(
        private val eventsRepository: EventsRepository,
        private val artistsRepository: ArtistsRepository,
        private val eventsMerger: EventsMerger
) {

    private val _eventsChannel: BroadcastChannel<List<Event>> = BroadcastChannel(10)

    val eventsChannel: BroadcastChannel<List<Event>>
        get() = _eventsChannel

    lateinit var source: SourceFilter

    suspend fun loadEventsForDateRange(startDate: Long, endDate: Long, favoriteArtistsOnly: Boolean = false) {
        val events = eventsRepository.getEventsForDateRange(startDate, endDate)

        val mergedEvents = getMergedEvents(events)
        val resultEvents = if (favoriteArtistsOnly) {
            mergedEvents.filter { it.favoriteArtist }
        } else {
            mergedEvents
        }
        _eventsChannel.send(resultEvents)
    }

    suspend fun loadEventsForArtist(artistName: String) {
        val events = eventsRepository.getEventsForArtist(artistName)

        _eventsChannel.send(getMergedEvents(events))
    }

    suspend fun addEventToPlanned(eventId: Long) {
        eventsRepository.addEventToPlanned(eventId)
        reloadEvents()
    }

    private suspend fun reloadEvents() {
        val searchFilter = source
        val events = when (searchFilter) {
            is SourceFilter.ByArtist -> eventsRepository.getEventsForArtist(searchFilter.artistName)
            is SourceFilter.ByDates -> eventsRepository.getEventsForDateRange(searchFilter.startDate, searchFilter.endDate)
        }

        val mergedEvents = getMergedEvents(events)

        _eventsChannel.send(mergedEvents)
    }

    private suspend fun getMergedEvents(events: List<EventEntry>): List<Event> {
        val plannedEventsIds = eventsRepository.getPlannedEventsIds()
        val favoriteArtists = artistsRepository.getFavoriteArtistsIds()

        return eventsMerger.getMergedEvents(events, favoriteArtists, plannedEventsIds)
    }
}

sealed class SourceFilter {

    data class ByDates(val startDate: Long, val endDate: Long) : SourceFilter()

    data class ByArtist(val artistName: String) : SourceFilter()
}