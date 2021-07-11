package ru.mrrobot1413.recipeapp.network.models

import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("number") val number: Int,
    @SerializedName("step") val step : String
)