package com.pyroblinchik.catapi.data.network.services

import com.pyroblinchik.catapi.data.network.model.BreedResponseDto
import com.pyroblinchik.catapi.data.network.model.PhotoResponseDto
import retrofit2.http.*

interface PhotoService {

    @GET("images/{photoId}")
    suspend fun getPhotoById(
        @Path("photoId") photoId: String
    ): PhotoResponseDto
}