package ru.mrrobot1413.recipeapp.network.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.mrrobot1413.recipeapp.network.Api
import ru.mrrobot1413.recipeapp.network.models.SearchRecipeResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(apiSrc: Api) {
    private val api = apiSrc

    suspend fun search(query: String, offset: Int): SearchRecipeResponse {
        return withContext(Dispatchers.IO){
            api.search(query = query, offset = offset)
        }
    }
}