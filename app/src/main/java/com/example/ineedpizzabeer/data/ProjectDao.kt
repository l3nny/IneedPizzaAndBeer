package com.example.ineedpizzabeer.data

import androidx.room.*
import com.example.ineedpizzabeer.domain.model.Businesses

@Dao
interface ProjectDao  {

    @Transaction
    suspend fun updateBusinesses(businesses: List<Businesses>) {
        businesses.let {
            deleteBusinesses()
            insertBusinesses(it)
        }
    }

    @Query("SELECT * FROM businesses_table ORDER BY name")
    fun getBusinesses(): List<Businesses?>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBusinesses(businesses: List<Businesses>)

    @Query("DELETE FROM businesses_table")
    suspend fun deleteBusinesses()
}