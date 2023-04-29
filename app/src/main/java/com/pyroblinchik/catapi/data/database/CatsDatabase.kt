package com.pyroblinchik.catapi.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pyroblinchik.catapi.data.database.dao.BreedsDao
import com.pyroblinchik.catapi.data.database.dao.PhotosDao
import com.pyroblinchik.catapi.data.database.model.BreedDBModel
import com.pyroblinchik.catapi.data.database.model.PhotoDBModel

@Database(
    entities = [
        BreedDBModel::class,
        PhotoDBModel::class
    ],
    version = 4
)
@TypeConverters(OfflineConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun breedsDao(): BreedsDao
    abstract fun photosDao(): PhotosDao

    companion object {

        private var db: AppDatabase? = null
        private const val DB_NAME = "main.db"
        private val LOCK = Any()

        fun getInstance(context: Context): AppDatabase {
            synchronized(LOCK) {
                db?.let { return it }
                val instance =
                    Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        DB_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                db = instance
                return instance
            }
        }
    }
}



