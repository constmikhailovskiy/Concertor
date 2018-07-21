package app.concertor.repo.events

import app.concertor.RetryHandler
import app.concertor.models.Event
import app.concertor.repository.EventsRepository
import app.concertor.source.EventsLocalStore
import app.concertor.source.EventsRemoteStore

class EventsRepositoryImpl(
        private val eventsLocalStore: EventsLocalStore,
        private val eventsRestStore: EventsRemoteStore,
        private val retryHandler: RetryHandler
) : EventsRepository {

    override suspend fun getEventsForArtist(artistName: String): List<Event> {
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

}
