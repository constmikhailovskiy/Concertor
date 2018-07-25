package app.concertor

import app.concertor.SongkickApi.ApiData.API_KEY
import app.concertor.models.EventsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SongkickApi {

    @GET("/api/3.0/events.json")
    fun getEvents(
            @Query("apikey") apiKey: String = API_KEY,
            @Query("artist_name") artistName: String? = null,
            @Query("location") location: String? = null,
            @Query("min_date") minDate: String? = null,
            @Query("max_date") maxDate: String? = null,
            @Query("page") page: Int = 1,
            @Query("per_page") perPage: Int = 20
    ): Single<EventsResponse>

    object ApiData {
        const val API_KEY = "UqncZSiBYPTv4CI0"
    }
}
