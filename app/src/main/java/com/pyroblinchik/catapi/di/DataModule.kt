package com.pyroblinchik.catapi.di

import android.app.Application
import com.pyroblinchik.catapi.data.database.AppDatabase
import com.pyroblinchik.catapi.data.database.dao.BreedsDao
import com.pyroblinchik.catapi.data.repository.BreedsRepositoryImpl
import com.pyroblinchik.catapi.di.base.ApplicationScope
import com.pyroblinchik.catapi.domain.breeds.BreedsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides


@Module
interface DataModule {

    @Binds
    @ApplicationScope
    fun bindBreedsRepository(impl: BreedsRepositoryImpl): BreedsRepository


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
