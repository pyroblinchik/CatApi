package com.pyroblinchik.catapi.data.database.dao

import androidx.room.*
import com.pyroblinchik.catapi.data.database.model.BreedDBModel

@Dao
interface BreedsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreed(obj: BreedDBModel)

    @Transaction
    @Query("SELECT b.* FROM breed b where id = :id")
    fun getBreedById(id:String) : BreedDBModel?

    @Transaction
    @Query("SELECT * FROM breed")
    fun getBreedsFavorites() : List<BreedDBModel>?


    @Query("DELETE FROM breed")
    fun deleteAll()

}