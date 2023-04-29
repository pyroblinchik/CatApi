package com.pyroblinchik.catapi.data.mapper


import com.pyroblinchik.catapi.data.network.model.PhotoResponseDto
import com.pyroblinchik.catapi.domain.base.models.Photo
import javax.inject.Inject
import kotlin.collections.ArrayList

class PhotosMapper @Inject constructor() {

    fun mapPhotoDtoModelToEntity(response: PhotoResponseDto) = Photo(
        response.id,
        response.url
    )
}






