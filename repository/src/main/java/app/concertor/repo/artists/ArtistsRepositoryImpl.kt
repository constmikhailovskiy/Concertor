package app.concertor.repo.artists

import app.concertor.models.Artist
import app.concertor.repository.ArtistsRepository
import app.concertor.source.ArtistsLocalStore

class ArtistsRepositoryImpl(
        private val favoriteArtistsLocalStore: ArtistsLocalStore
) : ArtistsRepository {

    override suspend fun getFavoriteArtistsIds(): List<Long> {
        return favoriteArtistsLocalStore.getFavoriteArtistsIds()
    }

    override suspend fun getFavoriteArtists(): List<Artist> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}