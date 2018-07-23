package app.concertor.source

import app.concertor.SongkickApi
import app.concertor.mappers.EventsMapper
import app.concertor.repository.models.EventEntry
import app.concertor.utils.DateUtils

interface EventsRemoteStore {

    suspend fun getEventsForArtist(artistName: String): List<EventEntry>

    suspend fun getEventsForDateRange(startDate: Long, endDate: Long): List<EventEntry>
}

class EventsRemoteStoreImpl(
        private val api: SongkickApi,
        private val mapper: EventsMapper
) : EventsRemoteStore {

    override suspend fun getEventsForArtist(artistName: String): List<EventEntry> {
        val restEvents = api.getEvents(artistName = artistName).await()
        return mapper.mapEvents(restEvents.resultsPage.results.events)
    }

    override suspend fun getEventsForDateRange(startDate: Long, endDate: Long): List<EventEntry> {
        val restEvents = api.getEvents(minDate = DateUtils.convertTimestampToFormattedDate(startDate),
                maxDate = DateUtils.convertTimestampToFormattedDate(endDate)).await()
        return mapper.mapEvents(restEvents.resultsPage.results.events)
    }
}
