package com.pyroblinchik.catapi.data.network.model



data class BreedResponseDto (
    var id: String = "0",
    var weight: WeightResponseDto? = null,
    var name: String? = null,
    var temperament: String? = null,
    var origin: String? = null,
    var country_codes: String? = null,
    var country_code: String? = null,
    var description: String? = null,
    var height: String? = null,
    var life_span: String? = null,
    var indoor: Int? = null,
    var lap: Int? = null,
    var alt_names: String? = null,
    var adaptability: Int? = null,
    var affection_level: Int? = null,
    var child_friendly: Int? = null,
    var dog_friendly: Int? = null,
    var energy_level: Int? = null,
    var grooming: Int? = null,
    var health_issues: Int? = null,
    var intelligence: Int? = null,
    var shedding_level: Int? = null,
    var social_needs: Int? = null,
    var stranger_friendly: Int? = null,
    var vocalisation: Int? = null,
    var experimental: Int? = null,
    var hairless: Int? = null,
    var natural: Int? = null,
    var rare: Int? = null,
    var rex: Int? = null,
    var suppressed_tail: Int? = null,
    var short_legs: Int? = null,
    var wikipedia_url: String? = null,
    var hypoallergenic: Int? = null,
    var reference_image_id: String? = null
)
