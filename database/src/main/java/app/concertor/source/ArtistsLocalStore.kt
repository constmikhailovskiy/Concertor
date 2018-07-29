package app.concertor.source

import app.concertor.AppDatabase

interface ArtistsLocalStore {

    fun getFavoriteArtistsIds(): List<Long>
}

class ArtistsLocalStoreImpl(
        private val appDatabase: AppDatabase
) : ArtistsLocalStore {

    override fun getFavoriteArtistsIds(): List<Long> {
        return appDatabase.getFavoriteArtistDao().selectAllFavoriteArtists().map { it.artistId }
    }
}
