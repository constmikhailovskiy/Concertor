package app.concertor.source

import app.concertor.AppDatabase
import app.concertor.mappers.EventsMapper
import app.concertor.repository.models.EventEntry
import io.reactivex.Completable
import io.reactivex.Single

interface EventsLocalStore {

    fun getEventsForArtist(artistName: String): Single<List<EventEntry>>

    fun getEventsForDateRange(startDate: Long, endDate: Long): Single<List<EventEntry>>

    fun saveEvents(events: List<EventEntry>): Completable
}

class EventsLocalStoreImpl(
        private val appDatabase: AppDatabase,
        private val mapper: EventsMapper
) : EventsLocalStore {

    override fun getEventsForArtist(artistName: String): Single<List<EventEntry>> {
        return appDatabase.getEventDao().selectEventsForArtist(artistName)
                .map { mapper.mapEventsToDomain(it) }
    }

    override fun getEventsForDateRange(startDate: Long, endDate: Long): Single<List<EventEntry>> {
        return appDatabase.getEventDao().selectEventsForDateRange(startDate, endDate)
                .map { mapper.mapEventsToDomain(it) }
    }

    override fun saveEvents(events: List<EventEntry>): Completable {
        return Completable.fromAction { appDatabase.getEventDao()
                .insert(events = mapper.mapEventsToLocal(events)) }
    }

}
