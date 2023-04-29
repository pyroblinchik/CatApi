package com.pyroblinchik.catapi.data.database.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.pyroblinchik.catapi.data.database.model.PhotoDBModel

@Dao
interface PhotosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPhoto(obj: PhotoDBModel)

    @Transaction
    @Query("SELECT b.* FROM photo b where id = :id")
    fun getPhotoById(id:String) : PhotoDBModel?

    @Query("DELETE FROM photo")
    fun deleteAll()

    @Query("DELETE FROM Photo WHERE id = :photoId")
    fun deletePhoto(photoId: String)
}