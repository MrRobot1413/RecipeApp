package ru.mrrobot1413.recipeapp.ui.homeScreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mrrobot1413.recipeapp.databinding.ListItemBinding
import ru.mrrobot1413.recipeapp.network.models.Recipe

class HomeRecyclerViewAdapter(private val clickListener: (id: Int, url: String) -> Unit) : RecyclerView.Adapter<HomeFragmentViewHolder>() {
    private val list: MutableList<Recipe> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setRecipes(list: List<Recipe>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setEmptyRecipes(list: List<Recipe>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeFragmentViewHolder {
        return HomeFragmentViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeFragmentViewHolder, position: Int) {
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