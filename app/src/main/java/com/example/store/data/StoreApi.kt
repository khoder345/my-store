package com.example.store.data

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

// غيّر هذا الرابط إلى رابط ملف apps.json المستضاف عندك
const val CATALOG_URL =
    "https://raw.githubusercontent.com/YOUR_USER/YOUR_REPO/main/apps.json"

val catalogJson = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
}

interface StoreApi {

    @GET
    suspend fun getCatalog(@Url url: String): Catalog

    companion object {
        val service: StoreApi by lazy {
            val client = OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build()

            Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/")
                .client(client)
                .addConverterFactory(
                    catalogJson.asConverterFactory("application/json".toMediaType())
                )
                .build()
                .create(StoreApi::class.java)
        }
    }
}
