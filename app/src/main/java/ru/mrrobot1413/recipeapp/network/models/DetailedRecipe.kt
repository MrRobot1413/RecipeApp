package ru.mrrobot1413.recipeapp.network.models

import com.google.gson.annotations.SerializedName

data class DetailedRecipe(
    @SerializedName("steps") var steps: List<Step>
)