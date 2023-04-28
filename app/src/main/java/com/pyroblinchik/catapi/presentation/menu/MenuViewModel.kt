package com.pyroblinchik.catapi.presentation.menu

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pyroblinchik.catapi.domain.base.models.BreedShort
import com.pyroblinchik.catapi.domain.breeds.GetBreedByIdFromNetworkUseCase
import com.pyroblinchik.catapi.domain.breeds.GetBreedsListFromNetworkUseCase
import com.pyroblinchik.catapi.domain.breedsFavorites.AddBreedToFavoritesDatabaseUseCase
import com.pyroblinchik.catapi.domain.breedsFavorites.GetBreedFavoriteByIdFromDatabaseUseCase
import com.pyroblinchik.catapi.domain.breedsFavorites.GetBreedsFavoritesListFromDatabaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class MenuViewModel @Inject constructor(
    private val getBreedsListFromNetworkUseCase: GetBreedsListFromNetworkUseCase,
    private val getBreedByIdFromNetworkUseCase: GetBreedByIdFromNetworkUseCase,
    private val addBreedToFavoritesDatabaseUseCase: AddBreedToFavoritesDatabaseUseCase,
    private val getBreedsFavoritesListFromDatabaseUseCase: GetBreedsFavoritesListFromDatabaseUseCase,
) : ViewModel() {

    val _activeTab = MutableLiveData<Int>(0)
    val activeTab: LiveData<Int>
        get() = _activeTab


    private val _breeds = MutableLiveData<List<BreedShort>>()
    val breeds: LiveData<List<BreedShort>>
        get() = _breeds

    private val _uiState = MutableStateFlow<MenuUIState>(MenuUIState.Empty)
    val uiState: StateFlow<MenuUIState> = _uiState

    init {

    }

    fun updateActiveTab(tab: Int) {
        _activeTab.value = tab
    }

}