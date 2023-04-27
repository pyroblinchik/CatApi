package com.pyroblinchik.catapi.data.network.services

import com.pyroblinchik.catapi.data.network.model.BreedResponseDto
import retrofit2.http.*

interface BreedService {

    @GET("breeds")
    suspend fun getBreeds(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        ): List<BreedResponseDto>

    @GET("breeds/{breedId}")
    suspend fun getBreedById(
        @Path("breedId") breedId: String
    ): BreedResponseDto
}