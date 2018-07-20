package app.concertor

import androidx.room.Database
import androidx.room.RoomDatabase
import app.concertor.dao.ArtistDao
import app.concertor.dao.EventDao
import app.concertor.entities.ArtistEntity
import app.concertor.entities.EventEntity

@Database(entities = [(EventEntity::class), (ArtistEntity::class)], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getEventDao(): EventDao

    abstract fun getArtistDao(): ArtistDao
}