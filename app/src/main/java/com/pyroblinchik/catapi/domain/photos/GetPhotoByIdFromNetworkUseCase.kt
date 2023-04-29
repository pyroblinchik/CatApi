package com.pyroblinchik.catapi.domain.photos

import com.pyroblinchik.catapi.domain.base.models.Breed
import javax.inject.Inject

class GetPhotoByIdFromNetworkUseCase @Inject constructor(
    private val repository: PhotosRepository
) {

    suspend operator fun invoke(breed: Breed) = repository.getPhotoByIdFromNetwork(breed)
}
