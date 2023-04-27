package com.pyroblinchik.catapi.data.mapper

import com.pyroblinchik.catapi.data.network.model.BreedResponseDto
import com.pyroblinchik.catapi.data.network.model.WeightResponseDto
import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.base.models.Weight
import kotlin.collections.ArrayList

class WeightMapper constructor() {


    fun mapWeightDtoModelToEntity(response: WeightResponseDto) = Weight(
        response.imperial,
        response.metric
    )
}






