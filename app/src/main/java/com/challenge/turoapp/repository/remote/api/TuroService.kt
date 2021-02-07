package com.challenge.turoapp.repository.remote.api

import com.challenge.turoapp.repository.remote.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * API service for retrieving businesses
 */
interface TuroService {

    @Headers("Authorization: Bearer 2ROaa2Rh9qu3WVTCms8FoVE4mSfHQHC7QJua95-kKT-PqzIlLSrs4tmHVdtdFw_66-JNfRiJmbCByHTvFNy5dQq-tpfS4FrPpupIzKlgELR3br-r5trpeFhrCRgwWnYx")
    @GET("v3/businesses/search")
    suspend fun fetchBusinesses(@Query("term") searchTerm: String,
                        @Query("location") location: String): Response<SearchResponse>
}