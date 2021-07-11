package ru.mrrobot1413.recipeapp.network.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await
import ru.mrrobot1413.recipeapp.network.Api
import ru.mrrobot1413.recipeapp.network.models.IngredientSearchResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IngredientsSearchRepository @Inject constructor(apiSrc: Api) {
    private val api = apiSrc

    suspend fun searchByIngredients(ingredients: String): List<IngredientSearchResponse> {
        return withContext(Dispatchers.IO){
            api.searchByIngredients(ingredients)
        }
    }
}