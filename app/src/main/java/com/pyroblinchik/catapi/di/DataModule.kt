package com.pyroblinchik.catapi.di

import android.app.Application
import com.pyroblinchik.catapi.data.database.AppDatabase
import com.pyroblinchik.catapi.data.database.dao.BreedsDao
import com.pyroblinchik.catapi.data.repository.BreedsFavoritesRepositoryImpl
import com.pyroblinchik.catapi.data.repository.BreedsRepositoryImpl
import com.pyroblinchik.catapi.di.base.ApplicationScope
import com.pyroblinchik.catapi.domain.breeds.BreedsRepository
import com.pyroblinchik.catapi.domain.breedsFavorites.BreedsFavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindBreedsRepository(impl: BreedsRepositoryImpl): BreedsRepository

    @Binds
    @ApplicationScope
    fun bindBreedsFavoritesRepository(impl: BreedsFavoritesRepositoryImpl): BreedsFavoritesRepository


    companion object {

        @Provides
        @ApplicationScope
        fun provideBreedsDao(
            application: Application,
        ): BreedsDao {
            return AppDatabase.getInstance(application).breedsDao()
        }
    }
}
