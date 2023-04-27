package com.pyroblinchik.catapi.domain.breedsFavorites

import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.base.models.BreedShort

interface BreedsFavoritesRepository {

    suspend fun getBreedsFavoritesList(): List<BreedShort>

    suspend fun getBreedFavoriteById(breedId: String): Breed

    suspend fun addBreedToFavorites(breed: Breed)
}