package app.concertor.source

import app.concertor.SongkickApi
import app.concertor.mappers.EventsMapper
import app.concertor.models.Event

interface RemoteEventsDataSource {

    suspend fun getEventsForArtist(artistName: String): List<Event>
}

class RemoteEventsDataSourceImpl(
        private val api: SongkickApi,
        private val mapper: EventsMapper
) : RemoteEventsDataSource {

    override suspend fun getEventsForArtist(artistName: String): List<Event> {
        val restEvents = api.getEvents(artistName = artistName).await()
        return mapper.mapEvents(restEvents.resultsPage.results.events)
    }
}
