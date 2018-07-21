package app.concertor.source

import app.concertor.AppDatabase
import app.concertor.CoroutinesContextProvider
import app.concertor.mappers.EventsMapper
import app.concertor.models.Event
import kotlinx.coroutines.experimental.withContext

interface EventsLocalStore {

    suspend fun getEventsForArtist(artistName: String): List<Event>

    suspend fun saveEvents(events: List<Event>)
}

class EventsLocalStoreImpl(
        private val appDatabase: AppDatabase,
        private val mapper: EventsMapper,
        private val coroutinesContextProvider: CoroutinesContextProvider
) : EventsLocalStore {

    override suspend fun getEventsForArtist(artistName: String): List<Event> {
        val localEvents = withContext(coroutinesContextProvider.IO) {
            appDatabase.getEventDao().selectEventsForArtist(artistName)
        }
        return mapper.mapEventsToDomain(localEvents)
    }

    override suspend fun saveEvents(events: List<Event>) {
        val localEvents = mapper.mapEventsToLocal(events)
        appDatabase.getEventDao().insert(events = localEvents)
    }

}
