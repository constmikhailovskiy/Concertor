package app.concertor.mappers

import app.concertor.models.Event
import app.concertor.models.EventModel

interface EventsMapper {

    fun mapEvents(eventModels: List<EventModel>): List<Event>

    fun mapEvent(eventModel: EventModel): Event
}

internal class EventsMapperImpl : EventsMapper {

    override fun mapEvents(eventModels: List<EventModel>): List<Event> {
        return eventModels.map { mapEvent(it) }
    }

    override fun mapEvent(eventModel: EventModel): Event {
        return Event(
                id = eventModel.id,
                name = eventModel.name,
                city = eventModel.location.city,
                venueName = eventModel.venue.name,
                url = eventModel.uri,
                artistName = eventModel.performance.artist.name,
                startDate = eventModel.start.date
        )
    }
}
