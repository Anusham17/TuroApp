package com.challenge.turoapp.repository.remote.model

import com.google.gson.annotations.SerializedName

/**
 * Data models for searched business result
 */
data class Business (
    val rating: Float,
    val price: String,
    val phone: String,
    val id: String,
    val alias: String,
    @SerializedName("is_closed")
    val isClosed: Boolean,
    @SerializedName("review_count")
    val reviewCount: Int,
    val name: String,
    val url: String,
    @SerializedName("image_url")
    val imageUrl: String?,
    val distance: Double,
    val categories: List<Category> = listOf(),
    val coordinates: Coordinates?,
    val location: Location?,
    val transactions: List<String> = listOf()
)

data class Category (
    val alias: String,
    val title: String
)

data class Coordinates (
    val latitude: Double,
    val longitude: Double
)

data class Location (
    val city: String,
    val country: String,
    val address2: String,
    val address3: String,
    val state: String,
    val address1: String,
    @SerializedName("zip_code")
    val zipCode: String
)