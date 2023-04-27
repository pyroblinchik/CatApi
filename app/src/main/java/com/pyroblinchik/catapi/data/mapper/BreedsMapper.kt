package com.pyroblinchik.catapi.data.mapper

import com.pyroblinchik.catapi.data.network.model.BreedResponseDto
import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.base.models.BreedShort
import kotlin.collections.ArrayList

class BreedsMapper constructor() {


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






