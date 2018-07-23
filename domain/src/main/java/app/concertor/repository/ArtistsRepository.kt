package app.concertor.repository

import app.concertor.models.Artist

interface ArtistsRepository {

    suspend fun getFavoriteArtistsIds(): List<Long>

    suspend fun getFavoriteArtists(): List<Artist>
}
