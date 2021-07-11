package ru.mrrobot1413.recipeapp.network.models

import com.google.gson.annotations.SerializedName

data class SearchRecipeResponse(
    @SerializedName("results")
    val list: List<Recipe>
)