package com.example.ineedpizzabeer.data

import com.example.ineedpizzabeer.domain.model.BusinessesResponse
import com.example.ineedpizzabeer.utils.Constants.GET_BUSINESSES
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {
    @GET(GET_BUSINESSES)
    suspend fun getBusinesses(
        @Query("term") term: String,
        @Query("latitude") latitude: Double? = 37.773972,
        @Query("longitude")  longitude: Double? = -122.431297,
    ): Response<BusinessesResponse>

}