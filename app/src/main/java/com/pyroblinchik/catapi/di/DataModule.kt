package com.pyroblinchik.catapi.di

import android.app.Application
import com.pyroblinchik.catapi.data.database.AppDatabase
import com.pyroblinchik.catapi.data.database.dao.BreedsDao
import com.pyroblinchik.catapi.data.database.dao.PhotosDao
import com.pyroblinchik.catapi.data.repository.BreedsRepositoryImpl
import com.pyroblinchik.catapi.data.repository.PhotosRepositoryImpl
import com.pyroblinchik.catapi.di.base.ApplicationScope
import com.pyroblinchik.catapi.domain.breeds.BreedsRepository
import com.pyroblinchik.catapi.domain.photos.PhotosRepository
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
    fun bindPhotosRepository(impl: PhotosRepositoryImpl): PhotosRepository

    companion object {

        @Provides
        @ApplicationScope
        fun provideBreedsDao(
            application: Application,
        ): BreedsDao {
            return AppDatabase.getInstance(application).breedsDao()
        }

        @Provides
        @ApplicationScope
        fun providePhotosDao(
            application: Application,
        ): PhotosDao {
            return AppDatabase.getInstance(application).photosDao()
        }
    }
}
