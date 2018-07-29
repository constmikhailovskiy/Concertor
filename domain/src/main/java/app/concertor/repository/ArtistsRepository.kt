package app.concertor.repository

import app.concertor.models.Artist

interface ArtistsRepository {

    fun getFavoriteArtistsIds(): List<Long>

    fun getFavoriteArtists(): List<Artist>
}
