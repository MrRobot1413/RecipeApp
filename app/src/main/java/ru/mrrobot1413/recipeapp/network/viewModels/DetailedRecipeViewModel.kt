package ru.mrrobot1413.recipeapp.network.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.mrrobot1413.recipeapp.network.models.DetailedRecipe
import ru.mrrobot1413.recipeapp.network.models.Step
import ru.mrrobot1413.recipeapp.network.repositories.DetailedRepository
import javax.inject.Inject

@HiltViewModel
class DetailedRecipeViewModel @Inject constructor(
    detailedRepositorySrc: DetailedRepository
) : ViewModel() {
    private val detailedRepo = detailedRepositorySrc

    private val _detailedRecipe = MutableStateFlow(
        DetailedRecipe(
            listOf(
                Step(
                    0, ""
                )
            )
        )
    )
    val detailedRecipe = _detailedRecipe

    fun getDetailsById(id: Int) {
        viewModelScope.launch {
            try {
                _detailedRecipe.value = detailedRepo.getDetailsById(id)[0]
            } catch (e: Exception) {
                Log.d("Exceptions", "Exception ${e.message}")
            }
        }
    }
}