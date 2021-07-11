package ru.mrrobot1413.recipeapp.network.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.mrrobot1413.recipeapp.network.models.Recipe
import ru.mrrobot1413.recipeapp.network.repositories.RecipeRepository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(recipeRepositorySrc: RecipeRepository) : ViewModel() {
    private val recipeRepository = recipeRepositorySrc

    private val _recipes = MutableStateFlow(listOf(Recipe(0, "", "")))
    val recipes = _recipes.asStateFlow()

    var offset = 0

    fun search(query: String, offset: Int) {
        if (query.length >= 3) {
            try {
                viewModelScope.launch {
                    _recipes.value = recipeRepository.search(query = query, offset = offset).list
                }
            } catch (e: Exception){
                Log.d("Exceptions", "Exception ${e.message}")
            }
        }
    }

//    fun getRandomRecipes(){
//        viewModelScope.launch {
//            _recipes.value = recipeRepository.getRandomRecipes().list
//        }
//    }
}