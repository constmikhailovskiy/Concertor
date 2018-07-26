package app.concertor.source.mappers

import app.concertor.repository.models.EventEntry
import app.concertor.models.EventModel
import app.concertor.repository.models.ArtistEntry
import app.concertor.repository.models.Location
import app.concertor.utils.DateUtils

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
            val performance = performance.first()
            EventEntry(id = id, name = name, location = Location(latitude = location.latitude,
                    longitude = location.longitude, city = location.city),
                    performanceName = performance.name, artistEntry = ArtistEntry(
                    performance.artist.id, performance.artist.name),
                    date = DateUtils.convertStringToDate(start.dateTime),
                    type = type, venue = venue.name, uri = uri
            )
        }
    }
}
