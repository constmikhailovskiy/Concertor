package app.concertor.source

import app.concertor.SongkickApi
import app.concertor.repository.models.EventEntry
import app.concertor.source.mappers.EventsRemoteMapper
import app.concertor.utils.DateUtils

interface EventsRemoteStore {

    suspend fun getEventsForArtist(artistName: String): List<EventEntry>

    suspend fun getEventsForDateRange(startDate: Long, endDate: Long): List<EventEntry>
}

class EventsRemoteStoreImpl(
        private val api: SongkickApi,
        private val mapper: EventsRemoteMapper
) : EventsRemoteStore {

    override suspend fun getEventsForArtist(artistName: String): List<EventEntry> {
        return api.getEvents(artistName = artistName).await().resultsPage.results.events.map {
            mapper.mapEvent(it)
        }
    }

    override suspend fun getEventsForDateRange(startDate: Long, endDate: Long): List<EventEntry> {
        return api.getEvents(minDate = DateUtils.convertTimestampToFormattedDate(startDate),
                maxDate = DateUtils.convertTimestampToFormattedDate(endDate)).await().resultsPage
                .results.events.map { mapper.mapEvent(it) }

    }
}
