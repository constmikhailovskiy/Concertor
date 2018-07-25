package app.concertor.source

import app.concertor.AppDatabase
import io.reactivex.Single

interface ArtistsLocalStore {

    fun getFavoriteArtistsIds(): Single<List<Long>>
}

class ArtistsLocalStoreImpl(
        private val appDatabase: AppDatabase
) : ArtistsLocalStore {

    override fun getFavoriteArtistsIds(): Single<List<Long>> {
        return appDatabase.getFavoriteArtistDao().selectAllFavoriteArtists().map { entries ->
            entries.map { it.artistId }
        }
    }
}
