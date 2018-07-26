package app.concertor.source

import app.concertor.SongkickApi
import app.concertor.repository.models.EventEntry
import app.concertor.source.mappers.EventsRemoteMapper
import app.concertor.utils.DateUtils
import io.reactivex.Single

interface EventsRemoteStore {

    fun getEventsForArtist(artistName: String): Single<List<EventEntry>>

    fun getEventsForDateRange(startDate: Long, endDate: Long): Single<List<EventEntry>>
}

class EventsRemoteStoreImpl(
        private val api: SongkickApi,
        private val mapper: EventsRemoteMapper
) : EventsRemoteStore {

    override fun getEventsForArtist(artistName: String): Single<List<EventEntry>> {
        return api.getEvents(artistName = artistName)
                .map { mapper.mapEvents(it.resultsPage.results.events) }
    }

    override fun getEventsForDateRange(startDate: Long, endDate: Long): Single<List<EventEntry>> {
        return api.getEvents(minDate = DateUtils.convertTimestampToFormattedDate(startDate),
                maxDate = DateUtils.convertTimestampToFormattedDate(endDate))
                .map { mapper.mapEvents(eventModels = it.resultsPage.results.events) }
    }
}
