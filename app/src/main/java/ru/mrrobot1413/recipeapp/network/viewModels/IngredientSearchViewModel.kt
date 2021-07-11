package ru.mrrobot1413.recipeapp.network.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import ru.mrrobot1413.recipeapp.network.models.IngredientSearchResponse
import ru.mrrobot1413.recipeapp.network.models.Recipe
import ru.mrrobot1413.recipeapp.network.repositories.IngredientsSearchRepository
import javax.inject.Inject

@HiltViewModel
class IngredientSearchViewModel @Inject constructor(ingredientsSearchRepo: IngredientsSearchRepository) :
    ViewModel() {
    private val ingredientsSearchRepository = ingredientsSearchRepo

    private val _recipes =
        MutableStateFlow(listOf(IngredientSearchResponse(0, "", "")))
    val recipes = _recipes

    fun searchByIngredients(ingredients: String) {
        try {
            viewModelScope.launch {
                _recipes.value =
                    ingredientsSearchRepository.searchByIngredients(ingredients)
            }
        } catch (e: Exception) {
            Log.d("Exceptions", "Exception ${e.message}")
        }
    }
}