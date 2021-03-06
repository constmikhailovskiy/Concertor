package app.concertor.dao

import androidx.room.*
import app.concertor.entities.EventEntity
import io.reactivex.Single

@Dao
abstract class EventDao {

    @Query("SELECT * FROM event WHERE artistName = :artistName ORDER BY date")
    abstract fun selectEventsForArtist(artistName: String): List<EventEntity>

    @Query("SELECT * FROM event WHERE date >= :startDate AND date <= :endDate ORDER BY date ASC")
    abstract fun selectEventsForDateRange(startDate: Long, endDate: Long): List<EventEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun insert(events: List<EventEntity>)
}
