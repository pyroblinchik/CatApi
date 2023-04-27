package com.pyroblinchik.catapi.data.repository

import android.app.Application
import com.pyroblinchik.catapi.data.mapper.BreedsMapper
import com.pyroblinchik.catapi.data.network.factory.BreedApiFactory
import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.base.models.BreedShort
import com.pyroblinchik.catapi.domain.breeds.BreedsRepository
import timber.log.Timber

class BreedsRepositoryImpl constructor(
    private val mapper: BreedsMapper,
    private val application: Application
) : BreedsRepository {

    override suspend fun getBreedsListFromNetwork(): List<BreedShort> {
        val result =
            BreedApiFactory().apiService.getBreeds(
                0,
                10
            )
        Timber.d(result.toString())

        val breeds = result.map {
            mapper.mapBreedShortDtoModelToEntity(it)
        }

        return breeds
    }

    override suspend fun getBreedByIdFromNetwork(breedId: String): Breed {
        val result =
            BreedApiFactory().apiService.getBreedById(
                breedId
            )
        Timber.d(result.toString())

        return mapper.mapBreedDtoModelToEntity(result)
    }


}
