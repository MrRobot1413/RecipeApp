package ru.mrrobot1413.recipeapp.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.mrrobot1413.recipeapp.network.models.DetailedRecipe
import ru.mrrobot1413.recipeapp.network.models.IngredientSearchResponse
import ru.mrrobot1413.recipeapp.network.models.SearchRecipeResponse
import ru.mrrobot1413.recipeapp.network.models.Step

interface Api {
    @GET("recipes/complexSearch")
    suspend fun search(
        @Query("query") query: String,
        @Query("offset") offset: Int,
        @Query("number") number: Int = 25
    ): SearchRecipeResponse

    @GET("recipes/findByIngredients")
    suspend fun searchByIngredients(
        @Query("ingredients") ingredients: String,
        @Query("number") number: Int = 50
    ): List<IngredientSearchResponse>

    @GET("recipes/{id}/analyzedInstructions")
    suspend fun getDetailsById(@Path("id") id: Int): List<DetailedRecipe>

//    @GET("recipes/random")
//    fun getRandomRecipes(@Query("apiKey") api: String = "20cccbdbf3964eb187b9e628442da973"): Call<RandomRecipesResponse>
}