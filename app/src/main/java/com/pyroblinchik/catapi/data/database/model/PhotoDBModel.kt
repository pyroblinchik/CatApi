package com.pyroblinchik.catapi.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pyroblinchik.catapi.domain.base.models.Weight

@Entity(
    tableName = "photo"
)
data class PhotoDBModel(
    @PrimaryKey
    var id: String,
    var url: String?
)