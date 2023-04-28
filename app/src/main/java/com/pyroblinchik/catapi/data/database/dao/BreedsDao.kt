package com.pyroblinchik.catapi.data.database.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.pyroblinchik.catapi.data.database.model.BreedDBModel

@Dao
interface BreedsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBreedFavorite(obj: BreedDBModel)

    @Transaction
    @Query("SELECT b.* FROM breed b where id = :id")
    fun getBreedById(id:String) : BreedDBModel?

    @Transaction
    @Query("SELECT * FROM breed b where isFavorite = 1")
    fun getBreedsFavorites() : List<BreedDBModel>?


    @Query("DELETE FROM breed")
    fun deleteAll()

    @Query("DELETE FROM breed WHERE id = :breedId")
    fun deleteBreed(breedId: String)
}