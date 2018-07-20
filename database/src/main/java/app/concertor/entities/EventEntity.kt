package app.concertor.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event")
data class EventEntity(
        @PrimaryKey @ColumnInfo(name = "id") val id: Long,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "type") val type: String,
        @ColumnInfo(name = "uri") val uri: String,
        @Embedded val venue: VenueEntity,
        @Embedded val location: LocationEntity,
        @Embedded val start: EventDateEntity,
        @ColumnInfo(name = "performanceName") val performanceName: String,
        @ColumnInfo(name = "artistName") val artistName: String,
        @ColumnInfo(name = "planned") val planned: Boolean
)

data class VenueEntity(@ColumnInfo(name = "displayName") val name: String)

data class LocationEntity(
        @ColumnInfo(name = "latitude") val latitude: Double,
        @ColumnInfo(name = "longitude") val longitude: Double,
        @ColumnInfo(name = "city") val city: String
)

data class EventDateEntity(
        @ColumnInfo(name = "time") val time: String,
        @ColumnInfo(name = "date") val date: String
)