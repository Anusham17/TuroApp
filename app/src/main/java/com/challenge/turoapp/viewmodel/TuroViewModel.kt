package com.challenge.turoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.challenge.turoapp.repository.Repository
import com.challenge.turoapp.repository.remote.model.Result

class TuroViewModel(val repository: Repository) : ViewModel() {

    fun fetchPizzaBeerBusiness(): LiveData<Result> = liveData {
        val data = repository.fetchPizzaBeerBusiness()
        emit(data)
    }
}

class TuroViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TuroViewModel::class.java)) {
            return TuroViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
