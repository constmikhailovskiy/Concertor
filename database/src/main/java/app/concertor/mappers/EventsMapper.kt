package app.concertor.mappers

import app.concertor.entities.EventEntity
import app.concertor.entities.LocationEntity
import app.concertor.entities.PerformanceEntity
import app.concertor.models.Event
import app.concertor.models.Location

interface EventsMapper {

    fun mapEventsToDomain(events: List<EventEntity>): List<Event>

    fun mapEventsToLocal(events: List<Event>): List<EventEntity>
}

class EventsMapperImpl : EventsMapper {

    override fun mapEventsToDomain(events: List<EventEntity>): List<Event> {
        return events.map { mapEventToDomain(it) }
    }

    override fun mapEventsToLocal(events: List<Event>): List<EventEntity> {
        return events.map { mapEventToLocal(it) }
    }

    private fun mapEventToDomain(event: EventEntity): Event {
        return with(event) {
            Event(id = id, name = name, uri = uri, venue = venue,
                    location = Location(
                            latitude = location.latitude,
                            longitude = location.longitude,
                            city = location.city),
                    date = date, artistName = performance.artistName,
                    performanceName = performance.performanceName, type = type)
        }
    }

    private fun mapEventToLocal(event: Event): EventEntity {
        return with(event) {
            EventEntity(id = id, name = name, uri = uri, venue = venue,
                    location = LocationEntity(
                            latitude = location.latitude,
                            longitude = location.longitude,
                            city = location.city),
                    date = date, performance = PerformanceEntity(artistName = artistName,
                    performanceName = performanceName), type = type)
        }
    }
}
