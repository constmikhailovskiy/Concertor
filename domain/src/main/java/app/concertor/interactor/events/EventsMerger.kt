package app.concertor.interactor.events

import app.concertor.models.Event
import app.concertor.models.Location
import app.concertor.repository.models.EventEntry

interface EventsMerger {

    fun getMergedEvents(
            events: List<EventEntry>,
            favoriteArtistsIds: List<Long>,
            plannedEventsIds: List<Long>
    ): List<Event>
}

class EventsMergerImpl : EventsMerger {

    override fun getMergedEvents(events: List<EventEntry>, favoriteArtistsIds: List<Long>, plannedEventsIds: List<Long>): List<Event> {
        return events.map { mapEvent(it, favoriteArtistsIds.contains(it.artistEntry.artistId),
                plannedEventsIds.contains(it.id)) }
    }

    private fun mapEvent(eventEntry: EventEntry, favoriteArtist: Boolean, plannedEvent: Boolean): Event {
        return with(eventEntry) {
            Event(id = id, name = name, uri = uri, venue = venue,
                    location = Location(
                            latitude = location.latitude,
                            longitude = location.longitude,
                            city = location.city),
                    date = date, artistName = artistEntry.artistName,
                    performanceName = performanceName,
                    type = type, favoriteArtist = favoriteArtist, planned = plannedEvent)
        }
    }

}