package app.concertor.repo.artists

import app.concertor.models.Artist
import app.concertor.repository.ArtistsRepository
import app.concertor.source.ArtistsLocalStore
import io.reactivex.Single

class ArtistsRepositoryImpl(
        private val favoriteArtistsLocalStore: ArtistsLocalStore
) : ArtistsRepository {

    override fun getFavoriteArtistsIds(): Single<List<Long>> {
        return favoriteArtistsLocalStore.getFavoriteArtistsIds()
    }

    override fun getFavoriteArtists(): Single<List<Artist>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}