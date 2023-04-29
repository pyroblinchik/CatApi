package com.pyroblinchik.catapi.domain.breeds

import javax.inject.Inject

class GetBreedByIdFromDatabaseUseCase @Inject constructor(
    private val repository: BreedsRepository
) {

    suspend operator fun invoke(breedId: String) = repository.getBreedByIdFromDatabase(breedId)
}
