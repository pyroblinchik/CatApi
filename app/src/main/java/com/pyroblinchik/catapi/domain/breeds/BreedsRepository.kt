package com.pyroblinchik.catapi.domain.breeds

import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.base.models.BreedShort

interface BreedsRepository {

    // NETWORK
    suspend fun getBreedsListFromNetwork(page: Int): List<BreedShort>

    suspend fun getBreedByIdFromNetwork(breedId: String): Breed

    // DATABASE
    suspend fun getBreedsFavoritesListFromDatabase(): List<BreedShort>

    suspend fun getBreedByIdFromDatabase(breedId: String): Breed

    suspend fun addBreedToFavoritesDatabase(breed: Breed)

    suspend fun deleteBreedFromDatabase(breedId: String)
}
