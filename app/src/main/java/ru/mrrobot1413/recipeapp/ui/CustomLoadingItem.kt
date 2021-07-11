package ru.mrrobot1413.recipeapp.ui

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.alexbykov.nopaginate.item.LoadingItem
import ru.mrrobot1413.recipeapp.databinding.ListItemLoadingBinding


class CustomLoadingItem : LoadingItem {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ListItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return object : RecyclerView.ViewHolder(binding.root) {}
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }
}