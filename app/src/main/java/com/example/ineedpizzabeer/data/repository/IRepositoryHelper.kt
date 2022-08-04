package com.example.ineedpizzabeer.data.repository

import com.example.ineedpizzabeer.domain.model.Businesses
import com.example.ineedpizzabeer.domain.model.BusinessesResponse
import com.example.ineedpizzabeer.utils.ViewStateResult

interface IRepositoryHelper {
    suspend fun getBusinesses(
        lat: Double,
        lon: Double,
        termSearch: String
    ): ViewStateResult<BusinessesResponse?>

    suspend fun getBusinessesDB(): ViewStateResult<List<Businesses?>>
    suspend fun updateBusinessesDB(list: List<Businesses>)
}