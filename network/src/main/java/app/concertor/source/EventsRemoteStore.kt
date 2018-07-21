package app.concertor.source

import app.concertor.SongkickApi
import app.concertor.mappers.EventsMapper
import app.concertor.models.Event

interface EventsRemoteStore {

    suspend fun getEventsForArtist(artistName: String): List<Event>
}

class EventsRemoteStoreImpl(
        private val api: SongkickApi,
        private val mapper: EventsMapper
) : EventsRemoteStore {

    override suspend fun getEventsForArtist(artistName: String): List<Event> {
        val restEvents = api.getEvents(artistName = artistName).await()
        return mapper.mapEvents(restEvents.resultsPage.results.events)
    }
}
