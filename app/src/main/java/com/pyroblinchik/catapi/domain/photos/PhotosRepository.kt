package com.pyroblinchik.catapi.domain.photos

import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.base.models.BreedShort
import com.pyroblinchik.catapi.domain.base.models.Photo

interface PhotosRepository {

    // NETWORK
    suspend fun getPhotoByIdFromNetwork(breed: Breed)
}
