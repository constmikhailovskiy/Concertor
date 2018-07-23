package app.concertor.source

import app.concertor.AppDatabase
import app.concertor.CoroutinesContextProvider
import app.concertor.mappers.EventsMapper
import app.concertor.repository.models.EventEntry
import kotlinx.coroutines.experimental.withContext

interface EventsLocalStore {

    suspend fun getEventsForArtist(artistName: String): List<EventEntry>

    suspend fun getEventsForDateRange(startDate: Long, endDate: Long): List<EventEntry>

    suspend fun saveEvents(events: List<EventEntry>)
}

class EventsLocalStoreImpl(
        private val appDatabase: AppDatabase,
        private val mapper: EventsMapper,
        private val coroutinesContextProvider: CoroutinesContextProvider
) : EventsLocalStore {

    override suspend fun getEventsForArtist(artistName: String): List<EventEntry> {
        val localEvents = withContext(coroutinesContextProvider.IO) {
            appDatabase.getEventDao().selectEventsForArtist(artistName)
        }
        return mapper.mapEventsToDomain(localEvents)
    }

    override suspend fun getEventsForDateRange(startDate: Long, endDate: Long): List<EventEntry> {
        val localEvents = withContext(coroutinesContextProvider.IO) {
            appDatabase.getEventDao().selectEventsForDateRange(startDate, endDate)
        }
        return mapper.mapEventsToDomain(localEvents)
    }

    override suspend fun saveEvents(events: List<EventEntry>) {
        val localEvents = mapper.mapEventsToLocal(events)
        withContext(coroutinesContextProvider.IO) {
            appDatabase.getEventDao().insert(events = localEvents)
        }
    }

}
