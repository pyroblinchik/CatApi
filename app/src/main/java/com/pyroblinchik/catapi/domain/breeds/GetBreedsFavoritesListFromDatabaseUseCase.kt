package com.pyroblinchik.catapi.domain.breeds

import javax.inject.Inject

class GetBreedsFavoritesListFromDatabaseUseCase @Inject constructor(
    private val repository: BreedsRepository
) {

    suspend operator fun invoke() = repository.getBreedsFavoritesListFromDatabase()
}
