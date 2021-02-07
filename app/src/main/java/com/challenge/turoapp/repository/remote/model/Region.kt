package com.challenge.turoapp.repository.remote.model

/**
 * Data model for Region
 */
data class Region (
    val center: Center
)

data class Center (
    val latitude: Double,
    val longitude: Double
)