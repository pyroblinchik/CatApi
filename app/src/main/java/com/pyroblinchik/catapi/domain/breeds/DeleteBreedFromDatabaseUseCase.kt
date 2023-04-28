package com.pyroblinchik.catapi.domain.breeds

import javax.inject.Inject

class DeleteBreedFromDatabaseUseCase @Inject constructor(
    private val repository: BreedsRepository
) {

    suspend operator fun invoke(breedId: String) = repository.deleteBreed(breedId)
}
