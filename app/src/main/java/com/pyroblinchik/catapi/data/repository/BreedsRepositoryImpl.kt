package com.pyroblinchik.catapi.data.repository

import android.app.Application
import com.pyroblinchik.catapi.data.Constants.PAGINATION_LIMIT
import com.pyroblinchik.catapi.data.database.dao.BreedsDao
import com.pyroblinchik.catapi.data.mapper.BreedsMapper
import com.pyroblinchik.catapi.data.network.factory.BreedApiFactory
import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.base.models.BreedShort
import com.pyroblinchik.catapi.domain.breeds.BreedsRepository
import timber.log.Timber
import javax.inject.Inject

class BreedsRepositoryImpl @Inject constructor(
    private val mapper: BreedsMapper,
    private val dao: BreedsDao
) : BreedsRepository {

    // NETWORK
    override suspend fun getBreedsListFromNetwork(page: Int): List<BreedShort> {
        val result =
            BreedApiFactory().apiService.getBreeds(
                page,
                PAGINATION_LIMIT
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
        val breed = mapper.mapBreedDtoModelToEntity(result)
        breed.isFavorite = checkBreedInFavorites(breed)
        return breed
    }

    // DATABASE
    override suspend fun getBreedsFavoritesListFromDatabase(): List<BreedShort> {
        val listDB = dao.getBreedsFavorites()
        val list = ArrayList<BreedShort>()
        listDB?.forEach {
            list.add(mapper.mapDbModelToEntityShort(it))
        }
        return list
    }

    override suspend fun getBreedByIdFromDatabase(breedId: String): Breed {
        val breed = dao.getBreedById(breedId)

        return mapper.mapDbModelToEntity(breed!!)
    }

    override suspend fun addBreedToFavoritesDatabase(breed: Breed) {
        dao.insertBreedFavorite(mapper.mapEntityToDBModel(breed))
    }

    override suspend fun deleteBreedFromDatabase(breedId: String) {
        dao.deleteBreed(breedId)
    }

    // SUB

    private fun checkBreedInFavorites(breed: Breed): Boolean {
        return dao.getBreedById(breed.id) != null
    }
}
