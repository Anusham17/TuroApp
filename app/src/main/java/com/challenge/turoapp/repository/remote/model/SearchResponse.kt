package com.challenge.turoapp.repository.remote.model

/**
 * Data model for success search result
 */
data class SearchResponse (
    val total: Int,
    val businesses: List<Business> = listOf(),
    val region: Region?
)