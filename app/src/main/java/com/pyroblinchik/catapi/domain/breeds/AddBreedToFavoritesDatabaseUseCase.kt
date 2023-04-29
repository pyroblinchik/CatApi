package com.pyroblinchik.catapi.domain.breeds

import com.pyroblinchik.catapi.domain.base.models.Breed
import javax.inject.Inject

class AddBreedToFavoritesDatabaseUseCase @Inject constructor(
    private val repository: BreedsRepository
) {

    suspend operator fun invoke(breed: Breed) = repository.addBreedToFavoritesDatabase(breed)
}
