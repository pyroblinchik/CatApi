package com.pyroblinchik.catapi.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pyroblinchik.catapi.domain.base.models.Weight

@Entity(
    tableName = "breed"
)
data class BreedDBModel(
    @PrimaryKey
    var id: String,
    @Embedded(prefix = "weight")
    var weight: Weight?,
    var name: String?,
    var temperament: String?,
    var origin: String?,
    var countryCodes: String?,
    var countryCode: String?,
    var description: String?,
    var height: String?,
    var lifeSpan: String?,
    var indoor: Int?,
    var lap: Int?,
    var altNames: String?,
    var adaptability: Int?,
    var affectionLevel: Int?,
    var childFriendly: Int?,
    var dogFriendly: Int?,
    var energyLevel: Int?,
    var grooming: Int?,
    var healthIssues: Int?,
    var intelligence: Int?,
    var sheddingLevel: Int?,
    var socialNeeds: Int?,
    var strangerFriendly: Int?,
    var vocalisation: Int?,
    var experimental: Int?,
    var hairless: Int?,
    var natural: Int?,
    var rare: Int?,
    var rex: Int?,
    var suppressedTail: Int?,
    var shortLegs: Int?,
    var wikipediaUrl: String?,
    var hypoallergenic: Int?,
    var referenceImageId: Int?
)