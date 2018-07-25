package app.concertor.repository

import app.concertor.models.Artist
import io.reactivex.Single

interface ArtistsRepository {

    fun getFavoriteArtistsIds(): Single<List<Long>>

    fun getFavoriteArtists(): Single<List<Artist>>
}
