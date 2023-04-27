package com.pyroblinchik.catapi.domain.breedsFavorites

import javax.inject.Inject

class GetBreedFavoriteByIdFromDatabaseUseCase @Inject constructor(
    private val repository: BreedsFavoritesRepository
) {

    suspend operator fun invoke(breedId: String) = repository.getBreedFavoriteById(breedId)
}
