package app.concertor.source

import app.concertor.AppDatabase
import app.concertor.CoroutinesContextProvider
import app.concertor.entities.PlannedEventEntity

interface PlannedEventsLocalStore {

    suspend fun addEventToPlanned(eventId: Long)

    suspend fun getPlannedEvents(): List<PlannedEventEntity>
}

class PlannedEventsLocalStoreImpl(
        private val appDatabase: AppDatabase,
        private val coroutinesContextProvider: CoroutinesContextProvider
) : PlannedEventsLocalStore {

    override suspend fun addEventToPlanned(eventId: Long) {
        with(coroutinesContextProvider.IO) {
            appDatabase.getPlannedEventDao().addPlannedEvent(PlannedEventEntity(eventId))
        }
    }

    override suspend fun getPlannedEvents(): List<PlannedEventEntity> {
        return with(coroutinesContextProvider.IO) {
            appDatabase.getPlannedEventDao().selectAllPlannedEvents()
        }
    }

}