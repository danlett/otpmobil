package hu.danlett.otpmobil.network.api

import hu.danlett.otpmobil.network.picture.model.NetworkPhotosResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFlickr {
    @GET("rest/")
    suspend fun getPhotos(
        @Query("method") method: String,
        @Query("api_key") apiKey: String,
        @Query("text") searchQuery: String?,
        @Query("per_page") perPage: Int,
        @Query("page") page: Int,
        @Query("format") format: String,
        @Query("nojsoncallback") noJsonCallback: Int
    ): NetworkPhotosResponse
}