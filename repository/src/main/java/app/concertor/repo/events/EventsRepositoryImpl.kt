package app.concertor.repo.events

import app.concertor.RetryHandler
import app.concertor.repository.models.EventEntry
import app.concertor.repository.EventsRepository
import app.concertor.source.EventsLocalStore
import app.concertor.source.EventsRemoteStore
import app.concertor.source.PlannedEventsLocalStore

class EventsRepositoryImpl(
        private val eventsLocalStore: EventsLocalStore,
        private val eventsRestStore: EventsRemoteStore,
        private val plannedEventsLocalStore: PlannedEventsLocalStore,
        private val retryHandler: RetryHandler
) : EventsRepository {

    override suspend fun getEventsForArtist(artistName: String): List<EventEntry> {
        val localEvents = eventsLocalStore.getEventsForArtist(artistName)
        return if (localEvents.isEmpty()) {
            retryHandler.retryIO {
                eventsRestStore.getEventsForArtist(artistName)
            }.apply {
                eventsLocalStore.saveEvents(this)
                eventsLocalStore.getEventsForArtist(artistName)
            }
        } else {
            localEvents
        }
    }

    override suspend fun getEventsForDateRange(startDate: Long, endDate: Long): List<EventEntry> {
        val localEvents = eventsLocalStore.getEventsForDateRange(startDate, endDate)
        return if (localEvents.isEmpty()) {
            retryHandler.retryIO {
                eventsRestStore.getEventsForDateRange(startDate, endDate)
            }.apply {
                eventsLocalStore.saveEvents(this)
                eventsLocalStore.getEventsForDateRange(startDate, endDate)
            }
        } else {
            localEvents
        }
    }

    override suspend fun getPlannedEventsIds(): List<Long> {
        return plannedEventsLocalStore.getPlannedEvents().map { it.eventId }
    }

    override suspend fun addEventToPlanned(eventId: Long) {
        plannedEventsLocalStore.addEventToPlanned(eventId)
    }

}
