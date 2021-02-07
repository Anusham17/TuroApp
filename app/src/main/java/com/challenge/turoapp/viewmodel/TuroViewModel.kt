package com.challenge.turoapp.viewmodel

import androidx.lifecycle.*
import com.challenge.turoapp.repository.Repository
import com.challenge.turoapp.repository.remote.model.Result
import kotlinx.coroutines.launch

class TuroViewModel(val repository: Repository) : ViewModel() {

    private val _businessLiveData = MutableLiveData<Result>()
    val businessLiveData: LiveData<Result> = _businessLiveData

    fun fetchPizzaBeerBusiness() {
        viewModelScope.launch {
            _businessLiveData.postValue(repository.fetchPizzaBeerBusiness())
        }
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
