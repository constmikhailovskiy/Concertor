package app.concertor.source

import app.concertor.AppDatabase
import app.concertor.mappers.EventsMapper
import app.concertor.models.Event
import kotlinx.coroutines.experimental.async

interface LocalEventDataSource {

    suspend fun getEventsForArtist(artistName: String): List<Event>
}

class LocalEventDataSourceImpl(
        private val appDatabase: AppDatabase,
        private val mapper: EventsMapper
) : LocalEventDataSource {

    override suspend fun getEventsForArtist(artistName: String): List<Event> {
        val localEvents = async { appDatabase.getEventDao().selectEventsForArtist(artistName) }.await()
        return mapper.mapEventsToDomain(localEvents)
    }

}
