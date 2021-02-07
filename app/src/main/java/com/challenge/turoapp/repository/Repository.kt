package com.challenge.turoapp.repository

import com.challenge.turoapp.repository.remote.model.Result

interface Repository {

    suspend fun fetchPizzaBeerBusiness(): Result
}