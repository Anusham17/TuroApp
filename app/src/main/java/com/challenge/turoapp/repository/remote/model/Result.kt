package com.challenge.turoapp.repository.remote.model

import java.lang.Exception

/**
 * Sealed class to handle Success and failure results
 */
sealed class Result {

    data class Success(val businessList: List<Business>?): Result()
    data class Error(val exception: TuroException): Result()
}

data class TuroException(
    val errorMessage: String?
) : Exception()