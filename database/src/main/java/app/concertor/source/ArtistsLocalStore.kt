package app.concertor.source

import app.concertor.AppDatabase
import app.concertor.CoroutinesContextProvider

interface ArtistsLocalStore {

    suspend fun getFavoriteArtistsIds(): List<Long>
}

class ArtistsLocalStoreImpl(
        private val appDatabase: AppDatabase,
        private val coroutinesContextProvider: CoroutinesContextProvider
) : ArtistsLocalStore {

    override suspend fun getFavoriteArtistsIds(): List<Long> {
        return with(coroutinesContextProvider.IO) {
            appDatabase.getFavoriteArtistDao().selectAllFavoriteArtists().map { it.artistId }
        }
    }

}
