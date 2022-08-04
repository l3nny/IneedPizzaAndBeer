package com.example.ineedpizzabeer.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.ineedpizzabeer.domain.model.Businesses
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = [Businesses::class], version = 1)
abstract class ProjectDataBase : RoomDatabase() {

    abstract fun getProjectDao(): ProjectDao

    class Callback @Inject constructor(
        private val database: Provider<ProjectDataBase>
    ) : RoomDatabase.Callback()
}
