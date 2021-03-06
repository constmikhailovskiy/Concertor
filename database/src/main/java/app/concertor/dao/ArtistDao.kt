package app.concertor.dao

import androidx.room.Dao
import androidx.room.Query
import app.concertor.entities.ArtistEntity
import io.reactivex.Single

@Dao
abstract class ArtistDao {

    @Query("SELECT * FROM artist WHERE name = :name")
    abstract fun findArtistsWithName(name: String): Single<List<ArtistEntity>>
}
