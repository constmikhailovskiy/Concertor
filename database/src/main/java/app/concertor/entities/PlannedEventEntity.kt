package app.concertor.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plannedEvent")
data class PlannedEventEntity(
        @PrimaryKey @ColumnInfo(name = "eventId") val eventId: Long
)
