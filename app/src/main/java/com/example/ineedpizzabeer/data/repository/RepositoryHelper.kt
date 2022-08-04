package com.example.ineedpizzabeer.data.repository

import com.example.ineedpizzabeer.data.ProjectDao
import com.example.ineedpizzabeer.domain.model.BusinessesResponse
import com.example.ineedpizzabeer.data.Service
import com.example.ineedpizzabeer.domain.model.Businesses
import com.example.ineedpizzabeer.utils.RequestErrorHandler
import com.example.ineedpizzabeer.utils.ResultException
import com.example.ineedpizzabeer.utils.ViewStateResult
import javax.inject.Inject

class RepositoryHelper @Inject constructor(
    private val service: Service,
    private  val dao: ProjectDao
) : IRepositoryHelper {
    override suspend fun getBusinesses(
        lat: Double,
        lon: Double,
        termSearch: String
    ): ViewStateResult<BusinessesResponse?> {
        return try {
            val result = service.getBusinesses(termSearch,lat,lon)
            if (result.isSuccessful) {
                ViewStateResult.Success(result.body())
            } else {
                ViewStateResult.Error(ResultException.Server(result.errorBody()))
            }
        } catch (e: Exception) {
            ViewStateResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }

    override suspend fun getBusinessesDB(): ViewStateResult<List<Businesses?>> {
        return try {
            val result = dao.getBusinesses()
            if (result.isNotEmpty()) {
                ViewStateResult.Success(result)
            } else {
                ViewStateResult.Error(ResultException.DataBase("You don't have previous data to show, please connect to the internet to add data"))
            }
        } catch (e: Exception) {
            ViewStateResult.Error(RequestErrorHandler.getRequestError(e))
        }
    }

    override suspend fun updateBusinessesDB(list: List<Businesses>) {
         dao.updateBusinesses(list)
    }
}