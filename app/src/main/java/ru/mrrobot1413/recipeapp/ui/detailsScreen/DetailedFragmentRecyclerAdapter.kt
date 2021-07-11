package ru.mrrobot1413.recipeapp.ui.detailsScreen

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.mrrobot1413.recipeapp.databinding.DetaeiledListItemBinding
import ru.mrrobot1413.recipeapp.network.models.Step

class DetailedFragmentRecyclerAdapter : RecyclerView.Adapter<DetailedFragmentViewHolder>() {
    val list: MutableList<Step> = mutableListOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setSteps(list: List<Step>) {
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailedFragmentViewHolder {
        return DetailedFragmentViewHolder(
            DetaeiledListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DetailedFragmentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}