package app.concertor.models

import java.util.*

data class Event(
        val id: Long,
        val name: String,
        val type: String,
        val uri: String,
        val venue: String,
        val location: Location,
        val date: Date,
        val performanceName: String,
        val artistName: String
)

data class Location(
        val latitude: Double,
        val longitude: Double,
        val city: String
)
