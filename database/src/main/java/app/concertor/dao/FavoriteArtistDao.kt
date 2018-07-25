package app.concertor.dao

import androidx.room.Dao
import androidx.room.Query
import app.concertor.entities.FavoriteArtistEntity
import io.reactivex.Single

@Dao
abstract class FavoriteArtistDao {

    @Query("SELECT * FROM favoriteArtist")
    abstract fun selectAllFavoriteArtists(): Single<List<FavoriteArtistEntity>>
}
