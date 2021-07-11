package ru.mrrobot1413.recipeapp.ui.ingredientSearch

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.alexbykov.nopaginate.paginate.NoPaginate
import ru.mrrobot1413.recipeapp.R
import ru.mrrobot1413.recipeapp.databinding.FragmentIngredientSearchBinding
import ru.mrrobot1413.recipeapp.misc.Utils
import ru.mrrobot1413.recipeapp.network.viewModels.IngredientSearchViewModel
import ru.mrrobot1413.recipeapp.ui.BottomNavigationManager
import ru.mrrobot1413.recipeapp.ui.CustomErrorItem
import ru.mrrobot1413.recipeapp.ui.CustomLoadingItem

@AndroidEntryPoint
class IngredientSearchFragment : Fragment() {
    private var binding: FragmentIngredientSearchBinding? = null
    private val ingredientSearchViewModel by lazy {
        ViewModelProvider(this).get(IngredientSearchViewModel::class.java)
    }
    private val adapter by lazy {
        IngredientSearchRecyclerViewAdapter { id, url ->
            (activity as BottomNavigationManager).openDetailedFragment(id, url, this)
        }
    }
    private val noConnectionSnackbar by lazy {
        Snackbar.make(requireView(), getString(R.string.error_occurred), Snackbar.LENGTH_INDEFINITE)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIngredientSearchBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()
        binding.let {
            if (it != null) {
                initViews(it)
            }
        }
    }

    private fun initViews(binding: FragmentIngredientSearchBinding) {
        (activity as BottomNavigationManager).showBottomNav()
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            attachRecyclerScroll()
            initSearchView(searchEt = searchEt)
        }
    }

    private fun initSearchView(searchEt: TextInputEditText) {
        searchEt.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                if (Utils.isInternetAvailable(requireContext())) {
                    noConnectionSnackbar.dismiss()
                    if (binding?.searchEt?.text.toString().trim().isNotEmpty()) {
                        showRecycler()

                        ingredientSearchViewModel.searchByIngredients(mapTextToQuery(binding?.searchEt?.text.toString()))
                    } else {
                        Snackbar.make(
                            requireView(),
                            getString(R.string.empty_field),
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    noConnectionSnackbar.show()
                }
                Utils.hideKeyboard(requireActivity())
                return@OnKeyListener true
            }
            false
        })
        searchEt.doOnTextChanged { _, _, _, count ->
            if (count == 0) {
                hideRecycler()
                adapter.setEmptyRecipes(mutableListOf())
            }
        }
    }

    private fun mapTextToQuery(text: String): String {
        return text.split(",").joinToString()
    }

    private fun showRecycler() {
        binding?.apply {
            recyclerView.isVisible = true
            txtSearchResults.isVisible = true
        }
    }

    private fun hideRecycler() {
        binding?.apply {
            recyclerView.isVisible = false
            txtSearchResults.isVisible = false
        }
    }

    private fun initObservers() {
        lifecycleScope.launch {
            ingredientSearchViewModel.recipes.collectLatest {
                adapter.setRecipes(it)
            }
        }
    }

    private fun attachRecyclerScroll() {
        NoPaginate.with(binding?.recyclerView!!)
            .setLoadingTriggerThreshold(5)
            .setCustomLoadingItem(CustomLoadingItem())
            .setCustomErrorItem(CustomErrorItem())
            .build()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}