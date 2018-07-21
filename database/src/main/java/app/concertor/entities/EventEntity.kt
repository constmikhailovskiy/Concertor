package app.concertor.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "event")
data class EventEntity(
        @PrimaryKey @ColumnInfo(name = "id") val id: Long,
        @ColumnInfo(name = "name") val name: String,
        @ColumnInfo(name = "type") val type: String,
        @ColumnInfo(name = "uri") val uri: String,
        @ColumnInfo(name = "venue") val venue: String,
        @Embedded val location: LocationEntity,
        @ColumnInfo(name = "date") val date: Date,
        @Embedded val performance: PerformanceEntity
)

data class LocationEntity(
        @ColumnInfo(name = "latitude") val latitude: Double,
        @ColumnInfo(name = "longitude") val longitude: Double,
        @ColumnInfo(name = "city") val city: String
)

data class PerformanceEntity(
        @ColumnInfo(name = "performanceName") val performanceName: String,
        @ColumnInfo(name = "artistName") val artistName: String
)
