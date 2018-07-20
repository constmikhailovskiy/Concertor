package app.concertor.dao

import androidx.room.*
import app.concertor.entities.EventEntity

@Dao
abstract class EventDao {

    @Query("SELECT * FROM event WHERE artistName = :artistName ORDER BY date")
    abstract fun selectEventsForArtist(artistName: String): List<EventEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(events: List<EventEntity>)
}
