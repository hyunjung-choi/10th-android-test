package com.prography.android.test.hyunjung.data.network

import android.util.Log
import com.prography.android.test.hyunjung.BuildConfig
import com.prography.android.test.hyunjung.data.model.Photo
import com.prography.android.test.hyunjung.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PhotoApiService @Inject constructor(
    private val client: HttpClient
) {
    private val baseUrl = Constants.BASE_URL
    private val accessKey = BuildConfig.UNSPLASH_ACCESS_KEY

    suspend fun getLatestPhotos(page: Int): List<Photo> {
        return try {
            val response: HttpResponse = client.get("$baseUrl/photos") {
                parameter("page", page)
                headers {
                    append("Authorization", "Client-ID $accessKey")
                }
            }
            val responseBody = response.body<String>()
            Log.d("API_RESPONSE", "Response Body: $responseBody")
            response.body()
        } catch (e: Exception) {
            emptyList()
        }
    }
}
