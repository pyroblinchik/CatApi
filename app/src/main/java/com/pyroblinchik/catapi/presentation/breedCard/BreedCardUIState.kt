package com.pyroblinchik.catapi.presentation.breedCard

import androidx.annotation.StringRes

sealed class BreedCardUIState {
    object Empty : BreedCardUIState()
    object Loading : BreedCardUIState()
    object Loaded : BreedCardUIState()
    object Finish : BreedCardUIState()
    class Error(val message: String) : BreedCardUIState()
}