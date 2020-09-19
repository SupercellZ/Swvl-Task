package com.example.swvl.data.source.network

import com.example.swvl.data.source.response.photoSearch.PhotosSearchResponse
import com.example.swvl.data.source.response.photoSize.PhotoSizeResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET(GET_METHOD_URL)
    suspend fun search(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("method") method: String = "flickr.photos.search",

        @Query("text") text: String,

        @Query("per_page") perPage: Int = 4
    ): PhotosSearchResponse

    @GET(GET_METHOD_URL)
    suspend fun getSizes(
        @Query("format") format: String = "json",
        @Query("nojsoncallback") nojsoncallback: Int = 1,
        @Query("api_key") apiKey: String = API_KEY,

        @Query("method") method: String = "flickr.photos.getSizes",
        @Query("photo_id") photoId: String
    ): PhotoSizeResponse

    companion object {
        private const val API_URL = "https://api.flickr.com/"
        private const val GET_METHOD_URL = "/services/rest/"
        private const val API_KEY = "4e3374c1199e356f6fa988a15fc31670"

        fun create(): MovieService {
            val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .client(getHttpClient())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

            return retrofit.create(MovieService::class.java)
        }

        private fun getHttpClient(): OkHttpClient {
            val okHttpBuilder = OkHttpClient.Builder()
            okHttpBuilder.addInterceptor { chain ->
                val requestWithUserAgent = chain.request().newBuilder()
                    .header("User-Agent", "My custom user agent")
                    .build()
                chain.proceed(requestWithUserAgent)
            }
            return okHttpBuilder.build()
        }
    }
}