package app.concertor.models

import com.squareup.moshi.Json
import java.util.*

data class EventsResponse(
        @Json(name = "resultsPage") val resultsPage: EventResultsPage
)

data class EventResultsPage(
        @Json(name = "results") val results: EventResults
)

data class EventResults(@Json(name = "event") val events: List<EventModel>)

data class EventModel(
        @Json(name = "displayName") val name: String,
        @Json(name = "type") val type: String,
        @Json(name = "uri") val uri: String,
        @Json(name = "venue") val venue: VenueModel,
        @Json(name = "location") val location: EventLocationModel,
        @Json(name = "start") val start: EventStartModel,
        @Json(name = "performance") val performance: PerformanceModel,
        @Json(name = "id") val id: Long
)

data class VenueModel(@Json(name = "displayName") val name: String)

data class EventLocationModel(
        @Json(name = "latitude") val latitude: Double,
        @Json(name = "longitude") val longitude: Double,
        @Json(name = "city") val city: String
)

data class EventStartModel(
        @Json(name = "time") val time: Date,
        @Json(name = "date") val date: Date,
        @Json(name = "datetime") val dateTime: Date
)

data class PerformanceModel(
        @Json(name = "artist") val artist: ArtistModel,
        @Json(name = "name") val name: String
)

data class ArtistModel(
        @Json(name = "uri") val uri: String,
        @Json(name = "displayName") val name: String,
        @Json(name = "id") val id: Long
)