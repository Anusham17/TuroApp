package com.challenge.turoapp.repository.remote

import com.challenge.turoapp.repository.remote.model.SearchResponse
import retrofit2.Response

/**
 * Remote data source
 */
interface RemoteSource {

    suspend fun fetchBusinesses(searchTerm: String, location: String): Response<SearchResponse>
}