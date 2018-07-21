package app.concertor

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import app.concertor.dao.ArtistDao
import app.concertor.dao.EventDao
import app.concertor.dao.FavoriteArtistDao
import app.concertor.dao.PlannedEventDao
import app.concertor.entities.ArtistEntity
import app.concertor.entities.EventEntity
import app.concertor.entities.FavoriteArtistEntity
import app.concertor.entities.PlannedEventEntity

@Database(entities = [
    (EventEntity::class), (ArtistEntity::class), (PlannedEventEntity::class),
    (FavoriteArtistEntity::class)
], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getEventDao(): EventDao

    abstract fun getPlannedEventDao(): PlannedEventDao

    abstract fun getArtistDao(): ArtistDao

    abstract fun getFavoriteArtistDao(): FavoriteArtistDao
}
