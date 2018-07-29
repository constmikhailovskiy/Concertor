package app.concertor.source

import app.concertor.AppDatabase
import app.concertor.source.mappers.EventsMapper
import app.concertor.repository.models.EventEntry
import io.reactivex.Completable
import io.reactivex.Single

interface EventsLocalStore {

    suspend fun getEventsForArtist(artistName: String): List<EventEntry>

    suspend fun getEventsForDateRange(startDate: Long, endDate: Long): List<EventEntry>

    suspend fun saveEvents(events: List<EventEntry>): Completable
}

class EventsLocalStoreImpl(
        private val appDatabase: AppDatabase,
        private val mapper: EventsMapper
) : EventsLocalStore {

    override suspend fun getEventsForArtist(artistName: String): List<EventEntry> {
        return appDatabase.getEventDao().selectEventsForArtist(artistName)
                .map { mapper.mapEventToDomain(it) }
    }

    override suspend fun getEventsForDateRange(startDate: Long, endDate: Long): List<EventEntry> {
        return appDatabase.getEventDao().selectEventsForDateRange(startDate, endDate)
                .map { mapper.mapEventToDomain(it) }
    }

    override suspend fun saveEvents(events: List<EventEntry>): Completable {
        return Completable.fromAction { appDatabase.getEventDao()
                .insert(events = mapper.mapEventsToLocal(events)) }
    }

}
