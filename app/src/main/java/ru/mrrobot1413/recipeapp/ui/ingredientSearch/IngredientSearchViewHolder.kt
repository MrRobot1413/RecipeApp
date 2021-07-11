package ru.mrrobot1413.recipeapp.ui.ingredientSearch

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.mrrobot1413.recipeapp.databinding.ListItemBinding
import ru.mrrobot1413.recipeapp.network.models.IngredientSearchResponse
import ru.mrrobot1413.recipeapp.network.models.Recipe

class IngredientSearchViewHolder(bindingSrc: ListItemBinding) :
    RecyclerView.ViewHolder(bindingSrc.root) {

    private val binding = bindingSrc
    val layout = binding.layout

    fun bind(recipe: IngredientSearchResponse) {
        binding.txtMealName.text = recipe.title
        Glide
            .with(binding.root)
            .load(recipe.image)
            .into(binding.img)
    }
}