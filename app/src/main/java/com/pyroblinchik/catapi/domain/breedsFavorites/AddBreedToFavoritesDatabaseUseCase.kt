package com.pyroblinchik.catapi.domain.breedsFavorites

import com.pyroblinchik.catapi.domain.base.models.Breed
import javax.inject.Inject

class AddBreedToFavoritesDatabaseUseCase @Inject constructor(
    private val repository: BreedsFavoritesRepository
) {

    suspend operator fun invoke(breed: Breed) = repository.addBreedToFavorites(breed)
}
