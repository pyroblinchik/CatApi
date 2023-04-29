package com.pyroblinchik.catapi.presentation.breedCard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pyroblinchik.catapi.domain.base.models.Breed
import com.pyroblinchik.catapi.domain.breeds.GetBreedByIdFromNetworkUseCase
import com.pyroblinchik.catapi.domain.breeds.AddBreedToFavoritesDatabaseUseCase
import com.pyroblinchik.catapi.domain.breeds.DeleteBreedFromDatabaseUseCase
import com.pyroblinchik.catapi.domain.breeds.GetBreedByIdFromDatabaseUseCase
import com.pyroblinchik.catapi.domain.photos.GetPhotoByIdFromNetworkUseCase
import com.pyroblinchik.catapi.presentation.breedCard.BreedCardActivity.Companion.MODE_FROM_FAVORITES
import com.pyroblinchik.catapi.presentation.breedCard.BreedCardActivity.Companion.MODE_FROM_NETWORK
import com.pyroblinchik.catapi.presentation.breedCard.BreedCardActivity.Companion.breedId
import com.pyroblinchik.catapi.presentation.breedCard.BreedCardActivity.Companion.requestCode
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class BreedCardViewModel @Inject constructor(
    private val getBreedByIdFromNetworkUseCase: GetBreedByIdFromNetworkUseCase,
    private val getPhotoByIdFromNetworkUseCase: GetPhotoByIdFromNetworkUseCase,
    private val getBreedFavoriteByIdFromDatabaseUseCase: GetBreedByIdFromDatabaseUseCase,
    private val addBreedToFavoritesDatabaseUseCase: AddBreedToFavoritesDatabaseUseCase,
    private val deleteBreedFromFavoritesDatabaseUseCase: DeleteBreedFromDatabaseUseCase
) : ViewModel() {

    private val _breed = MutableLiveData<Breed>()
    val breed: LiveData<Breed>
        get() = _breed

    private val _uiState = MutableStateFlow<BreedCardUIState>(BreedCardUIState.Empty)
    val uiState: StateFlow<BreedCardUIState> = _uiState

    init {
        getBreed()
    }

    private fun getBreed(){
        when(requestCode){
            MODE_FROM_NETWORK ->{
                getBreedFromNetwork()
            }
            MODE_FROM_FAVORITES ->{
                getBreedFavoritesFromDatabase()
            }
            else ->{
                throw java.lang.IllegalArgumentException("$requestCode")
            }
        }
    }

    private fun getBreedFromNetwork() {
        _uiState.value = BreedCardUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val breed = getBreedByIdFromNetworkUseCase.invoke(breedId)
                _breed.postValue(breed)
                _uiState.value = BreedCardUIState.Loaded

            } catch (error: Exception) {
                _uiState.value = BreedCardUIState.Error(error.printStackTrace().toString())
            }
        }
    }

    fun downloadBreedPhotoFromNetwork() {
        _uiState.value = BreedCardUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {

                getPhotoByIdFromNetworkUseCase.invoke(breed.value!!)
                _uiState.value = BreedCardUIState.Loaded

            } catch (error: Exception) {
                _uiState.value = BreedCardUIState.Error(error.printStackTrace().toString())
            }
        }
    }

    private fun getBreedFavoritesFromDatabase() {
        _uiState.value = BreedCardUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {

                val breed = getBreedFavoriteByIdFromDatabaseUseCase.invoke(breedId)
                _breed.postValue(breed)
                _uiState.value = BreedCardUIState.Loaded

            } catch (error: Exception) {
                _uiState.value = BreedCardUIState.Error(error.printStackTrace().toString())
            }
        }
    }

    fun addBreedToFavorites() {
        _uiState.value = BreedCardUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {

                addBreedToFavoritesDatabaseUseCase.invoke(_breed.value!!)
                getBreedFromNetwork()

            } catch (error: Exception) {
                _uiState.value = BreedCardUIState.Error(error.printStackTrace().toString())
            }
        }
    }

    fun deleteBreedFromFavorites() {
        _uiState.value = BreedCardUIState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when(requestCode){
                    MODE_FROM_NETWORK ->{
                        deleteBreedFromFavoritesDatabaseUseCase.invoke(_breed.value!!.id)
                        getBreedFromNetwork()
                    }
                    MODE_FROM_FAVORITES ->{
                        deleteBreedFromFavoritesDatabaseUseCase.invoke(_breed.value!!.id)
                        _uiState.value = BreedCardUIState.Finish
                    }
                    else ->{
                        throw java.lang.IllegalArgumentException("$requestCode")
                    }
                }

            } catch (error: Exception) {
                _uiState.value = BreedCardUIState.Error(error.printStackTrace().toString())
            }
        }
    }

}