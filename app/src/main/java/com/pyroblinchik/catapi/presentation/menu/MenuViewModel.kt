package com.pyroblinchik.catapi.presentation.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pyroblinchik.catapi.domain.base.models.BreedShort
import com.pyroblinchik.catapi.domain.breeds.GetBreedByIdFromNetworkUseCase
import com.pyroblinchik.catapi.domain.breeds.GetBreedsListFromNetworkUseCase
import com.pyroblinchik.catapi.domain.breeds.AddBreedToFavoritesDatabaseUseCase
import com.pyroblinchik.catapi.domain.breeds.GetBreedsFavoritesListFromDatabaseUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    private val _breedsFavorites = MutableLiveData<List<BreedShort>>()
    val breedsFavorites: LiveData<List<BreedShort>>
        get() = _breedsFavorites

    private val _uiState = MutableStateFlow<MenuUIState>(MenuUIState.Empty)
    val uiState: StateFlow<MenuUIState> = _uiState

    init {
//        getBreeds()
    }

    fun getBreeds() {
        _uiState.value = MenuUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val breeds = getBreedsListFromNetworkUseCase.invoke()
                _breeds.postValue(breeds)
                _uiState.value = MenuUIState.Loaded

            } catch (error: Exception) {
                _uiState.value = MenuUIState.Error(error.printStackTrace().toString())
            }
        }
    }

    fun getBreedsFavorites() {
        _uiState.value = MenuUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val breedsFavorites = getBreedsFavoritesListFromDatabaseUseCase.invoke()
                _breedsFavorites.postValue(breedsFavorites)
                _uiState.value = MenuUIState.Loaded

            } catch (error: Exception) {
                _uiState.value = MenuUIState.Error(error.printStackTrace().toString())
            }
        }
    }

    fun updateActiveTab(tab: Int) {
        _activeTab.value = tab
    }

}