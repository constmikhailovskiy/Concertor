package app.concertor.repository

import app.concertor.models.Event

interface EventsRepository {

    suspend fun getEventsForArtist(artistName: String): List<Event>
}
