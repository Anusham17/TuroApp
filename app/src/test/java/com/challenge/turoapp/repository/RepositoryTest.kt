package com.challenge.turoapp.repository

import com.challenge.turoapp.repository.remote.RemoteSource
import com.challenge.turoapp.repository.remote.model.Business
import com.challenge.turoapp.repository.remote.model.SearchResponse
import com.challenge.turoapp.repository.remote.model.Result
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mockito
import retrofit2.Response

class RepositoryTest {

    private var remoteSource = Mockito.mock(RemoteSource::class.java)
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = RepositoryImpl(remoteSource)
    }

    @Test
    fun `fetch business success`() = runBlocking {
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

        Mockito.`when`(remoteSource.fetchBusinesses(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(Response.success(SearchResponse(total = 40, businesses = responseList, region = null)))

        val expectedResult: Result = repository.fetchPizzaBeerBusiness()
        assertTrue(expectedResult is Result.Success)
        val expectedSuccessResult = expectedResult as Result.Success
        assertEquals(expectedSuccessResult.businessList, listOf(business, business))
    }

    @Test
    fun `fetch business error`() = runBlocking {
        Mockito.`when`(remoteSource.fetchBusinesses(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(Response.error(500, ResponseBody.create(null, "Server Down")))

        val expectedResult: Result = repository.fetchPizzaBeerBusiness()
        assertTrue(expectedResult is Result.Error)

        val expectedErrorResult = expectedResult as Result.Error
        assertEquals(expectedErrorResult.exception.errorMessage, "No businesses for the provided location!!")
    }
}