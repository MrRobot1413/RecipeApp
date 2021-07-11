package ru.mrrobot1413.recipeapp.ui.ingredientSearch

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mrrobot1413.recipeapp.databinding.ListItemBinding
import ru.mrrobot1413.recipeapp.network.models.IngredientSearchResponse
import ru.mrrobot1413.recipeapp.network.models.Recipe

class IngredientSearchRecyclerViewAdapter(private val clickListener: (id: Int, url: String) -> Unit) : RecyclerView.Adapter<IngredientSearchViewHolder>() {
    private val list: MutableList<IngredientSearchResponse> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setRecipes(list: List<IngredientSearchResponse>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setEmptyRecipes(list: List<IngredientSearchResponse>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientSearchViewHolder {
        return IngredientSearchViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: IngredientSearchViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.layout.setOnClickListener {
            clickListener(item.id, item.image)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}