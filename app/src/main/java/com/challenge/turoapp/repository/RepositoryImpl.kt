package com.challenge.turoapp.repository

import com.challenge.turoapp.repository.remote.Constants
import com.challenge.turoapp.repository.remote.RemoteSource
import com.challenge.turoapp.repository.remote.model.Business
import com.challenge.turoapp.repository.remote.model.Result
import com.challenge.turoapp.repository.remote.model.TuroException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async

class RepositoryImpl(private val remoteSource: RemoteSource) : Repository {

    override suspend fun fetchPizzaBeerBusiness(): Result {
        val businessList = mutableListOf<Business>()
        val pizzaDeferred = CoroutineScope(Dispatchers.IO).async {
            remoteSource.fetchBusinesses(Constants.PIZZA_KEYWORD, Constants.LOCATION)
        }

        val beerDeferred = CoroutineScope(Dispatchers.IO).async {
            remoteSource.fetchBusinesses(Constants.BEER_KEYWORD, Constants.LOCATION)
        }

        val pizzaResults = pizzaDeferred.await()
        if (pizzaResults.isSuccessful) {
            pizzaResults.body()?.let {
                businessList.addAll(it.businesses)
            }
        }
        val beerResults = beerDeferred.await()
        if (beerResults.isSuccessful) {
            beerResults.body()?.let {
                businessList.addAll(it.businesses)
            }
        }
        if (businessList.isNullOrEmpty()) {
            return Result.Error(TuroException("No businesses for the provided location!!"))
        }
        return Result.Success(businessList)
    }
}