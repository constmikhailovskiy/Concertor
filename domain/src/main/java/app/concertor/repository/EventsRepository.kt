package app.concertor.repository

import app.concertor.repository.models.EventEntry
import io.reactivex.Completable
import io.reactivex.Single

interface EventsRepository {

    fun getEventsForArtist(artistName: String): Single<List<EventEntry>>

    fun getEventsForDateRange(startDate: Long, endDate: Long): Single<List<EventEntry>>

    fun getPlannedEventsIds(): Single<List<Long>>

    fun addEventToPlanned(eventId: Long): Completable
}
