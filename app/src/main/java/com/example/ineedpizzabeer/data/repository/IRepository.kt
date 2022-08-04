package com.example.ineedpizzabeer.data.repository

import com.example.ineedpizzabeer.domain.model.Businesses
import com.example.ineedpizzabeer.domain.model.BusinessesResponse
import com.example.ineedpizzabeer.utils.ViewStateResult
import kotlinx.coroutines.flow.Flow

interface IRepository {
    fun getBusinesses(
        lat: Double,
        lon: Double,
        termSearch: String
    ): Flow<ViewStateResult<BusinessesResponse?>>

    fun getBusinessesDB(): Flow<ViewStateResult<List<Businesses?>>>

    suspend fun updateBusinessesDB(list: List<Businesses>)
}