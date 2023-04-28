package com.pyroblinchik.catapi.presentation.menu

import androidx.annotation.StringRes

sealed class MenuUIState {
    object Empty : MenuUIState()
    object Loading : MenuUIState()
    object Loaded : MenuUIState()
    class Error(val message: String) : MenuUIState()
}