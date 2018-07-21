package app.concertor.mappers

import app.concertor.models.Event
import app.concertor.models.EventModel
import app.concertor.models.Location

interface EventsMapper {

    fun mapEvents(eventModels: List<EventModel>): List<Event>

    fun mapEvent(eventModel: EventModel): Event
}

internal class EventsMapperImpl : EventsMapper {

    override fun mapEvents(eventModels: List<EventModel>): List<Event> {
        return eventModels.map { mapEvent(it) }
    }

    override fun mapEvent(eventModel: EventModel): Event {
        return with(eventModel) {
            Event(id = id, name = name, location = Location(latitude = location.latitude,
                    longitude = location.longitude, city = location.city),
                    performanceName = performance.name, artistName = performance.artist.name,
                    date = start.dateTime, type = type, venue = venue.name, uri = uri
            )
        }
    }
}
