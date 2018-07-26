package app.concertor.source.mappers

import app.concertor.repository.models.EventEntry
import app.concertor.models.EventModel
import app.concertor.repository.models.ArtistEntry
import app.concertor.repository.models.Location

interface EventsRemoteMapper {

    fun mapEvents(eventModels: List<EventModel>): List<EventEntry>

    fun mapEvent(eventModel: EventModel): EventEntry
}

internal class EventsRemoteMapperImpl : EventsRemoteMapper {

    override fun mapEvents(eventModels: List<EventModel>): List<EventEntry> {
        return eventModels.map { mapEvent(it) }
    }

    override fun mapEvent(eventModel: EventModel): EventEntry {
        return with(eventModel) {
            EventEntry(id = id, name = name, location = Location(latitude = location.latitude,
                    longitude = location.longitude, city = location.city),
                    performanceName = performance.name, artistEntry = ArtistEntry(
                    performance.artist.id, performance.artist.name), date = start.dateTime,
                    type = type, venue = venue.name, uri = uri
            )
        }
    }
}
