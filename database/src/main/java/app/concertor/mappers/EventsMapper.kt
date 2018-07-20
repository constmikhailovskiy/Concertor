package app.concertor.mappers

import app.concertor.entities.EventEntity
import app.concertor.models.Event

interface EventsMapper {

    fun mapEventsToDomain(events: List<EventEntity>): List<Event>
}

class EventsMapperImpl : EventsMapper {

    override fun mapEventsToDomain(events: List<EventEntity>): List<Event> {
        return events.map { mapEventToDomain(it) }
    }

    private fun mapEventToDomain(event: EventEntity): Event {
        return Event(id = event.id, name = event.name, url = event.uri,
                venueName = event.venue.name, city = event.location.city,
                startDate = event.start.date, artistName = event.artistName)
    }
}
