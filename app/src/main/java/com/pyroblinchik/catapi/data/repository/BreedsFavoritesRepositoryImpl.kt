package com.pyroblinchik.catapi.data.repository

import android.app.Application
import com.pyroblinchik.catapi.data.database.dao.BreedsDao
import com.pyroblinchik.catapi.data.database.model.BreedDBModel
import com.pyroblinchik.catapi.data.mapper.BreedsMapper
import com.pyroblinchik.catapi.data.network.factory.BreedApiFactory
import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.base.models.BreedShort
import com.pyroblinchik.catapi.domain.breeds.BreedsRepository
import com.pyroblinchik.catapi.domain.breedsFavorites.BreedsFavoritesRepository
import timber.log.Timber

class BreedsFavoritesRepositoryImpl constructor(
    private val mapper: BreedsMapper,
    private val dao: BreedsDao
) : BreedsFavoritesRepository {

    override suspend fun getBreedsFavoritesList(): List<BreedShort> {
        val listDB = dao.getBreedsFavorites()
        val list = ArrayList<BreedShort>()
        listDB?.forEach {
            list.add(mapper.mapDbModelToEntityShort(it))
        }
        return list
    }

    override suspend fun getBreedFavoriteById(breedId: String): Breed {
        val breed = dao.getBreedById(breedId)

        return mapper.mapDbModelToEntity(breed!!)
    }

    override suspend fun addBreedToFavorites(breed: Breed) {
        dao.insertBreed(mapper.mapEntityToDBModel(breed))
    }


}
