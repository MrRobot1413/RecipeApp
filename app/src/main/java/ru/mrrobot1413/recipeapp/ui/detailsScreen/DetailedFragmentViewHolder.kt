package ru.mrrobot1413.recipeapp.ui.detailsScreen

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import ru.mrrobot1413.recipeapp.databinding.DetaeiledListItemBinding
import ru.mrrobot1413.recipeapp.network.models.Step

class DetailedFragmentViewHolder(bindingSrc: DetaeiledListItemBinding) : RecyclerView.ViewHolder(bindingSrc.root) {
    private val binding = bindingSrc

    @SuppressLint("SetTextI18n")
    fun bind(response: Step) {
        binding.txtNum.text = response.number.toString() + "."
        binding.txtStep.text = response.step
    }
}