package com.challenge.turoapp.repository.remote

import com.challenge.turoapp.repository.remote.api.TuroService
import com.challenge.turoapp.repository.remote.model.SearchResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RemoteSourceImpl(private val turoService: TuroService): RemoteSource {

    companion object {
        fun create(baseURL: String): RemoteSource {
            // Initialization of Retrofit instance
            val retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(TuroService::class.java)
            return RemoteSourceImpl(service)
        }
    }

    override suspend fun fetchBusinesses(searchTerm: String, location: String): Response<SearchResponse> =
        turoService.fetchBusinesses(searchTerm, location)
}