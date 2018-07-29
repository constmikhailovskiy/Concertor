package app.concertor.source.mappers

import app.concertor.entities.EventEntity
import app.concertor.entities.LocationEntity
import app.concertor.entities.PerformanceEntity
import app.concertor.repository.models.ArtistEntry
import app.concertor.repository.models.EventEntry
import app.concertor.repository.models.Location

interface EventsMapper {

    fun mapEventsToDomain(events: List<EventEntity>): List<EventEntry>

    fun mapEventToDomain(event: EventEntity): EventEntry

    fun mapEventsToLocal(events: List<EventEntry>): List<EventEntity>
}

class EventsMapperImpl : EventsMapper {

    override fun mapEventsToDomain(events: List<EventEntity>): List<EventEntry> {
        return events.map { mapEventToDomain(it) }
    }

    override fun mapEventsToLocal(events: List<EventEntry>): List<EventEntity> {
        return events.map { mapEventToLocal(it) }
    }

    override fun mapEventToDomain(event: EventEntity): EventEntry {
        return with(event) {
            EventEntry(id = id, name = name, uri = uri, venue = venue,
                    location = Location(
                            latitude = location.latitude,
                            longitude = location.longitude,
                            city = location.city),
                    date = date, artistEntry = ArtistEntry(performance.artistId,
                    performance.artistName), performanceName = performance.performanceName,
                    type = type)
        }
    }

    private fun mapEventToLocal(event: EventEntry): EventEntity {
        return with(event) {
            EventEntity(id = id, name = name, uri = uri, venue = venue,
                    location = LocationEntity(
                            latitude = location.latitude,
                            longitude = location.longitude,
                            city = location.city),
                    date = date, performance = PerformanceEntity(
                    artistName = artistEntry.artistName, artistId = artistEntry.artistId,
                    performanceName = performanceName), type = type)
        }
    }
}
