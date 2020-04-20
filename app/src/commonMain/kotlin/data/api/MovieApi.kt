package data.api

import data.model.MovieContainer
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.http.Url
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class MovieApi {
    private val client = HttpClient()

    private var address = Url("https://api.themoviedb.org/3/movie/popular?api_key=220e2ce24c38e16c4eafe5708e0e39d4")

    suspend fun getMovies(): MovieContainer {
        val json = Json(JsonConfiguration(strictMode = false))
        return json.parse(MovieContainer.serializer(), client.get {
            url(address.toString())
        })
    }
}