package app.concertor.source

import app.concertor.AppDatabase
import app.concertor.entities.PlannedEventEntity
import io.reactivex.Completable
import io.reactivex.Single

interface PlannedEventsLocalStore {

    fun addEventToPlanned(eventId: Long)

    fun getPlannedEvents(): List<PlannedEventEntity>
}

class PlannedEventsLocalStoreImpl(
        private val appDatabase: AppDatabase
) : PlannedEventsLocalStore {

    override fun addEventToPlanned(eventId: Long) {
        appDatabase.getPlannedEventDao().addPlannedEvent(PlannedEventEntity(eventId))
    }

    override fun getPlannedEvents(): List<PlannedEventEntity> {
        return appDatabase.getPlannedEventDao().selectAllPlannedEvents()
    }

}