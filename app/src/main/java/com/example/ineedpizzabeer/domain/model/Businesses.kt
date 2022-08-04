package com.example.ineedpizzabeer.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class BusinessesResponse(var businesses: List<Businesses>? = null)

@Entity(tableName = "Businesses_table")
data class Businesses(
    @PrimaryKey
    var id: String,
    var alias: String? = null,
    var name: String? = null,
    var image_url: String? = null,
    var is_closed: Boolean? = null,
    var url: String? = null,
    var review_count: Int? = null,
    var distance: Double? = null,
)

data class BusinessesRequest(
    var term: String? = null,
    var latitude: Double? = null,
    var longitude: Double? = null
)
