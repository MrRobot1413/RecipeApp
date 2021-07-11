package ru.mrrobot1413.recipeapp.ui


import ru.alexbykov.nopaginate.callback.OnRepeatListener
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.alexbykov.nopaginate.item.ErrorItem
import ru.mrrobot1413.recipeapp.databinding.ListItemErrorBinding


class CustomErrorItem : ErrorItem {
    private lateinit var binding: ListItemErrorBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        binding = ListItemErrorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return object : RecyclerView.ViewHolder(binding.root) {}
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        repeatListener: OnRepeatListener
    ) {
        binding.btnRetry.setOnClickListener {
            repeatListener.onClickRepeat()
        }
    }
}