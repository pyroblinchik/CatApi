package com.pyroblinchik.catapi.data.repository

import android.app.Application
import com.pyroblinchik.catapi.data.mapper.PhotosMapper
import com.pyroblinchik.catapi.data.network.factory.PhotoApiFactory
import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.base.models.Photo
import com.pyroblinchik.catapi.domain.photos.PhotosRepository
import com.pyroblinchik.catapi.util.files.DownloadManagerForPhotos
import timber.log.Timber
import javax.inject.Inject

class PhotosRepositoryImpl @Inject constructor(
    private val application: Application,
    private val mapper: PhotosMapper
) : PhotosRepository {

    // NETWORK

    override suspend fun getPhotoByIdFromNetwork(breed: Breed) {
        val result =
            PhotoApiFactory().apiService.getPhotoById(
                breed.referenceImageId!!
            )

        DownloadManagerForPhotos(application,breed).downloadImage(result.url!!)
    }
}
