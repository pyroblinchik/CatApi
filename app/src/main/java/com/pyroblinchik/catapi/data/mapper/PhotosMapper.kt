package com.pyroblinchik.catapi.data.mapper


import com.pyroblinchik.catapi.data.database.model.PhotoDBModel
import com.pyroblinchik.catapi.data.network.model.PhotoResponseDto
import com.pyroblinchik.catapi.domain.base.models.Photo
import javax.inject.Inject
import kotlin.collections.ArrayList

class PhotosMapper @Inject constructor() {

    fun mapEntityToDBModel(photo: Photo) = PhotoDBModel(
        photo.id,
        photo.url
    )

    fun mapDBModelToEntity(photo: PhotoDBModel) = Photo(
        photo.id,
        photo.url
    )

    fun mapPhotoDtoModelToEntity(response: PhotoResponseDto) = Photo(
        response.id,
        response.url
    )
}






