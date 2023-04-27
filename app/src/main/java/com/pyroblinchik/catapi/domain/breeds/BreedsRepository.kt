package com.pyroblinchik.catapi.domain.breeds

import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.base.models.BreedShort

interface BreedsRepository {

    suspend fun getBreedsListFromNetwork(): List<BreedShort>

    suspend fun getBreedByIdFromNetwork(breedId: String): Breed
}
