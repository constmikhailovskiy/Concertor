package app.concertor.source

import app.concertor.AppDatabase
import app.concertor.entities.PlannedEventEntity
import io.reactivex.Completable
import io.reactivex.Single

interface PlannedEventsLocalStore {

    fun addEventToPlanned(eventId: Long): Completable

    fun getPlannedEvents(): Single<List<PlannedEventEntity>>
}

class PlannedEventsLocalStoreImpl(
        private val appDatabase: AppDatabase
) : PlannedEventsLocalStore {

    override fun addEventToPlanned(eventId: Long): Completable {
        return Completable.fromAction {
            appDatabase.getPlannedEventDao().addPlannedEvent(PlannedEventEntity(eventId))
        }
    }

    override fun getPlannedEvents(): Single<List<PlannedEventEntity>> {
        return appDatabase.getPlannedEventDao().selectAllPlannedEvents()
    }

}