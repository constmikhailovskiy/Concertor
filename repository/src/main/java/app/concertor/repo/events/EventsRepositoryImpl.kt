package app.concertor.repo.events

import app.concertor.repository.EventsRepository
import app.concertor.repository.models.EventEntry
import app.concertor.source.EventsLocalStore
import app.concertor.source.EventsRemoteStore
import app.concertor.source.PlannedEventsLocalStore
import io.reactivex.Completable
import io.reactivex.Single

class EventsRepositoryImpl(
        private val eventsLocalStore: EventsLocalStore,
        private val eventsRestStore: EventsRemoteStore,
        private val plannedEventsLocalStore: PlannedEventsLocalStore
) : EventsRepository {

    override suspend fun getEventsForArtist(artistName: String): List<EventEntry> {
        val localEventsForArtist = eventsLocalStore.getEventsForArtist(artistName)

        return if (localEventsForArtist.isEmpty()) {
            eventsRestStore.getEventsForArtist(artistName)
        } else {
            localEventsForArtist
        }
    }

    override suspend fun getEventsForDateRange(startDate: Long, endDate: Long): List<EventEntry> {
        val localEvents = eventsLocalStore.getEventsForDateRange(startDate, endDate)

        return if (localEvents.isEmpty()) {
            eventsRestStore.getEventsForDateRange(startDate, endDate)
        } else {
            localEvents
        }
    }

    override suspend fun getPlannedEventsIds(): List<Long> {
        return plannedEventsLocalStore.getPlannedEvents().map { it.eventId }
    }

    override suspend fun addEventToPlanned(eventId: Long) {
        return plannedEventsLocalStore.addEventToPlanned(eventId)
    }

}
