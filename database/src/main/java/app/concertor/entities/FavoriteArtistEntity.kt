package app.concertor.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favoriteArtist")
data class FavoriteArtistEntity(
        @PrimaryKey @ColumnInfo(name = "artistId") val artistId: Long
)
