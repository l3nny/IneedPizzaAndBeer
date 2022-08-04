package com.example.ineedpizzabeer.utils.di

import com.example.ineedpizzabeer.data.ProjectDao
import com.example.ineedpizzabeer.data.Service
import com.example.ineedpizzabeer.data.repository.IRepository
import com.example.ineedpizzabeer.data.repository.Repository
import com.example.ineedpizzabeer.data.repository.RepositoryHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun providerRepository(api: Service,dao:ProjectDao): IRepository {
        val dataSourceImpl = RepositoryHelper(api,dao)
        return Repository(dataSourceImpl)
    }
}