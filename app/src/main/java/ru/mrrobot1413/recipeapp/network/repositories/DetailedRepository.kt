package ru.mrrobot1413.recipeapp.network.repositories

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.await
import ru.mrrobot1413.recipeapp.network.Api
import ru.mrrobot1413.recipeapp.network.models.DetailedRecipe
import ru.mrrobot1413.recipeapp.network.models.Step
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailedRepository @Inject constructor(apiSrc: Api) {
    private val api = apiSrc

    suspend fun getDetailsById(id: Int): List<DetailedRecipe> {
        return withContext(Dispatchers.IO) {
            api.getDetailsById(id)
        }
    }
}