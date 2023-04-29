package com.pyroblinchik.catapi.domain.breeds

import javax.inject.Inject

class GetBreedsListFromNetworkUseCase @Inject constructor(
    private val repository: BreedsRepository
) {

    suspend operator fun invoke(page: Int) = repository.getBreedsListFromNetwork(page)
}
