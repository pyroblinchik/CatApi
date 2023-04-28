package com.pyroblinchik.catapi.domain.breeds

import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.base.models.BreedShort

interface BreedsRepository {

    // NETWORK
    suspend fun getBreedsListFromNetwork(): List<BreedShort>

    suspend fun getBreedByIdFromNetwork(breedId: String): Breed

    // DATABASE
    suspend fun getBreedsFavoritesList(): List<BreedShort>

    suspend fun getBreedById(breedId: String): Breed

    suspend fun addBreedToFavorites(breed: Breed)

    suspend fun deleteBreed(breedId: String)
}
