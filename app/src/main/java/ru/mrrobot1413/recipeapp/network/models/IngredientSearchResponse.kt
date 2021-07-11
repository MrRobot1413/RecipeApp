package ru.mrrobot1413.recipeapp.network.models

import com.google.gson.annotations.SerializedName

data class IngredientSearchResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("image") val image: String
)