package com.example.ineedpizzabeer.utils.di

import android.content.Context
import androidx.room.Room
import com.example.ineedpizzabeer.data.ProjectDao
import com.example.ineedpizzabeer.data.ProjectDataBase
import com.example.ineedpizzabeer.utils.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ProjectDataBase {
        return Room.databaseBuilder(
            context,
            ProjectDataBase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    fun provideDao(database: ProjectDataBase): ProjectDao {
        return database.getProjectDao()
    }
}