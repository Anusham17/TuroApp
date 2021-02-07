package com.challenge.turoapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.challenge.turoapp.TestCoroutineRule
import com.challenge.turoapp.repository.Repository
import com.challenge.turoapp.repository.remote.model.Business
import com.challenge.turoapp.repository.remote.model.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times

@ExperimentalCoroutinesApi
class TuroViewModelTest {

    @get:Rule
    internal val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = TestCoroutineRule()

    private var repository = mock(Repository::class.java)
    private lateinit var viewModel: TuroViewModel
    private lateinit var mockObserver: Observer<Result>


    @Before
    fun setUp() {
        viewModel = TuroViewModel(repository)
        mockObserver = mock(Observer::class.java) as Observer<Result>
    }

    @Test
    fun `fetch meanings`() = coroutineTestRule.runBlockingTest {
        viewModel.fetchPizzaBeerBusiness().observeForever(mockObserver)
        val business = Business(
            4.5F,
            "$",
            "+14152520800",
            "E8RJkjfdcwgtyoPMjQ_Olg",
            "four-barrel-coffee-san-francisco", false,
            1738,
            "Four Barrel Coffee",
            "https://www.yelp.com/biz/four-barrel-coffee-san-francisco",
            "http://s3-media2.fl.yelpcdn.com/bphoto/MmgtASP3l_t4tPCL1iAsCg/o.jpg",
            1604.23, coordinates = null, location = null
        )
        val responseList = listOf(business)

        Mockito.`when`(repository.fetchPizzaBeerBusiness())
            .thenReturn(Result.Success(responseList))

        Mockito.verify(mockObserver, times(1)).onChanged(any<Result>())
    }
}