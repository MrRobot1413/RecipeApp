package ru.mrrobot1413.recipeapp.ui.detailsScreen

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mrrobot1413.recipeapp.databinding.FragmentDetailedRecipeBinding
import ru.mrrobot1413.recipeapp.misc.Constants
import ru.mrrobot1413.recipeapp.network.viewModels.DetailedRecipeViewModel
import ru.mrrobot1413.recipeapp.ui.BottomNavigationManager
import androidx.recyclerview.widget.DividerItemDecoration
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import ru.mrrobot1413.recipeapp.R


@AndroidEntryPoint
class DetailedRecipeFragment : Fragment() {

    private var binding: FragmentDetailedRecipeBinding? = null
    private val detailedRecipeVm by lazy {
        ViewModelProvider(this).get(DetailedRecipeViewModel::class.java)
    }
    private val adapter by lazy {
        DetailedFragmentRecyclerAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailedRecipeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            detailedRecipeVm.detailedRecipe.collectLatest {
                adapter.setSteps(it.steps)
            }
        }
        arguments?.getInt(Constants.ID)?.let { detailedRecipeVm.getDetailsById(it) }
    }

    private fun initViews() {
        (activity as BottomNavigationManager).hideBottomNav()

        binding?.apply {
            binding?.img?.let {
                Glide
                    .with(requireContext())
                    .asBitmap()
                    .load(arguments?.getString(Constants.URL))
                    .error(R.drawable.ic_baseline_error_24)
                    .into(object: CustomTarget<Bitmap>(){
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            it.setImageBitmap(resource)
                            progress.isVisible = false
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                            progress.isVisible = false
                        }
                    })
            }

            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
            val dividerItemDecoration = DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
            recyclerView.addItemDecoration(dividerItemDecoration)

            toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        (activity as BottomNavigationManager).showBottomNav()
    }
}