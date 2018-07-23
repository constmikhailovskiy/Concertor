package app.concertor.repository.models

import java.util.*

data class EventEntry(
        val id: Long,
        val name: String,
        val type: String,
        val uri: String,
        val venue: String,
        val location: Location,
        val date: Date,
        val performanceName: String,
        val artistEntry: ArtistEntry
)

data class ArtistEntry(val artistId: Long, val artistName: String)

data class Location(
        val latitude: Double,
        val longitude: Double,
        val city: String
)
