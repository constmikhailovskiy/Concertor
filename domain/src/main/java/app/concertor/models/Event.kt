package app.concertor.models

data class Event(
        val id: Long,
        val name: String,
        val url: String,
        val venueName: String,
        val city: String,
        val startDate: String,
        val artistName: String
)
