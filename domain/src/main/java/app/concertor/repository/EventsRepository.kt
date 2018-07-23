package app.concertor.repository

import app.concertor.repository.models.EventEntry

interface EventsRepository {

    suspend fun getEventsForArtist(artistName: String): List<EventEntry>

    suspend fun getEventsForDateRange(startDate: Long, endDate: Long): List<EventEntry>

    suspend fun getPlannedEventsIds(): List<Long>

    suspend fun addEventToPlanned(eventId: Long)
}
