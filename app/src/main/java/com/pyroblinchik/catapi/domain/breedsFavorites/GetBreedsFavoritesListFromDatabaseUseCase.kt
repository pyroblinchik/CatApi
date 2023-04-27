package com.pyroblinchik.catapi.domain.breedsFavorites

import javax.inject.Inject

class GetBreedsFavoritesListFromDatabaseUseCase @Inject constructor(
    private val repository: BreedsFavoritesRepository
) {

    suspend operator fun invoke() = repository.getBreedsFavoritesList()
}
