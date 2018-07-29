package app.concertor.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.concertor.entities.PlannedEventEntity
import io.reactivex.Single

@Dao
abstract class PlannedEventDao {

    @Query("SELECT * FROM plannedEvent")
    abstract fun selectAllPlannedEvents(): List<PlannedEventEntity>

    @Query("SELECT * FROM plannedEvent WHERE eventId = :eventId LIMIT 1")
    abstract fun selectPlannedEventsById(eventId: Long): List<PlannedEventEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun addPlannedEvent(plannedEvent: PlannedEventEntity)
}
