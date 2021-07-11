package ru.mrrobot1413.recipeapp.ui.homeScreen

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.mrrobot1413.recipeapp.databinding.ListItemBinding
import ru.mrrobot1413.recipeapp.network.models.Recipe

class HomeFragmentViewHolder(bindingSrc: ListItemBinding) :
    RecyclerView.ViewHolder(bindingSrc.root) {

    private val binding = bindingSrc
    val layout = binding.layout

    fun bind(recipe: Recipe) {
        binding.txtMealName.text = recipe.title
        Glide
            .with(binding.root)
            .load(recipe.image)
            .into(binding.img)
    }
}