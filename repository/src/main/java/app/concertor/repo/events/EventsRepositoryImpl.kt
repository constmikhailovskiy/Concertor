package app.concertor.repo.events

import app.concertor.repository.EventsRepository
import app.concertor.repository.models.EventEntry
import app.concertor.source.EventsLocalStore
import app.concertor.source.EventsRemoteStore
import app.concertor.source.PlannedEventsLocalStore
import io.reactivex.Completable
import io.reactivex.Single

class EventsRepositoryImpl(
        private val eventsLocalStore: EventsLocalStore,
        private val eventsRestStore: EventsRemoteStore,
        private val plannedEventsLocalStore: PlannedEventsLocalStore
) : EventsRepository {

    override fun getEventsForArtist(artistName: String): Single<List<EventEntry>> {
        return eventsLocalStore.getEventsForArtist(artistName)
                .flatMap {
                    if (it.isEmpty()) {
                        eventsRestStore.getEventsForArtist(artistName)
                    } else {
                        Single.just(it)
                    }
                }
    }

    override fun getEventsForDateRange(startDate: Long, endDate: Long): Single<List<EventEntry>> {
        return eventsLocalStore.getEventsForDateRange(startDate, endDate)
                .flatMap {
                    if (it.isEmpty()) {
                        eventsRestStore.getEventsForDateRange(startDate, endDate)
                    } else {
                        Single.just(it)
                    }
                }
    }

    override fun getPlannedEventsIds(): Single<List<Long>> {
        return plannedEventsLocalStore.getPlannedEvents()
                .map { events -> events.map { it.eventId } }
    }

    override fun addEventToPlanned(eventId: Long): Completable {
        return plannedEventsLocalStore.addEventToPlanned(eventId)
    }

}
