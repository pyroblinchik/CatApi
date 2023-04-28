package com.pyroblinchik.catapi.data.mapper

import com.pyroblinchik.catapi.data.database.model.BreedDBModel
import com.pyroblinchik.catapi.data.network.model.BreedResponseDto
import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.base.models.BreedShort
import javax.inject.Inject
import kotlin.collections.ArrayList

class BreedsMapper @Inject constructor() {

    fun mapDbModelToEntityShort(dbModel: BreedDBModel) = BreedShort(
        dbModel.id,
        dbModel.name
    )

    fun mapDbModelToEntity(dbModel: BreedDBModel) = Breed(
        dbModel.id,
        dbModel.weight,
        dbModel.name,
        dbModel.temperament,
        dbModel.origin,
        dbModel.countryCodes,
        dbModel.countryCode,
        dbModel.description,
        dbModel.height,
        dbModel.lifeSpan,
        dbModel.indoor,
        dbModel.lap,
        dbModel.altNames,
        dbModel.adaptability,
        dbModel.affectionLevel,
        dbModel.childFriendly,
        dbModel.dogFriendly,
        dbModel.energyLevel,
        dbModel.grooming,
        dbModel.healthIssues,
        dbModel.intelligence,
        dbModel.sheddingLevel,
        dbModel.socialNeeds,
        dbModel.strangerFriendly,
        dbModel.vocalisation,
        dbModel.experimental,
        dbModel.hairless,
        dbModel.natural,
        dbModel.rare,
        dbModel.rex,
        dbModel.suppressedTail,
        dbModel.shortLegs,
        dbModel.wikipediaUrl,
        dbModel.hypoallergenic,
        dbModel.referenceImageId
    )

    fun mapEntityToDBModel(breed: Breed) = BreedDBModel(
        breed.id,
        breed.weight,
        breed.name,
        breed.temperament,
        breed.origin,
        breed.countryCodes,
        breed.countryCode,
        breed.description,
        breed.height,
        breed.lifeSpan,
        breed.indoor,
        breed.lap,
        breed.altNames,
        breed.adaptability,
        breed.affectionLevel,
        breed.childFriendly,
        breed.dogFriendly,
        breed.energyLevel,
        breed.grooming,
        breed.healthIssues,
        breed.intelligence,
        breed.sheddingLevel,
        breed.socialNeeds,
        breed.strangerFriendly,
        breed.vocalisation,
        breed.experimental,
        breed.hairless,
        breed.natural,
        breed.rare,
        breed.rex,
        breed.suppressedTail,
        breed.shortLegs,
        breed.wikipediaUrl,
        breed.hypoallergenic,
        breed.referenceImageId
    )

    fun mapBreedDtoModelToEntity(response: BreedResponseDto) = Breed(
        response.id,
        WeightMapper().mapWeightDtoModelToEntity(response.weight!!),
        response.name,
        response.temperament,
        response.origin,
        response.country_codes,
        response.country_code,
        response.description,
        response.height,
        response.life_span,
        response.indoor,
        response.lap,
        response.alt_names,
        response.adaptability,
        response.affection_level,
        response.child_friendly,
        response.dog_friendly,
        response.energy_level,
        response.grooming,
        response.health_issues,
        response.intelligence,
        response.shedding_level,
        response.social_needs,
        response.stranger_friendly,
        response.vocalisation,
        response.experimental,
        response.hairless,
        response.natural,
        response.rare,
        response.rex,
        response.suppressed_tail,
        response.short_legs,
        response.wikipedia_url,
        response.hypoallergenic,
        response.reference_image_id
    )

    fun mapBreedShortDtoModelToEntity(response: BreedResponseDto) = BreedShort(
        response.id,
        response.name
    )
}






