package ru.mrrobot1413.recipeapp.ui.homeScreen

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.mrrobot1413.recipeapp.databinding.FragmentHomeBinding
import ru.mrrobot1413.recipeapp.network.viewModels.HomeViewModel
import androidx.core.view.isVisible
import ru.alexbykov.nopaginate.paginate.NoPaginate
import com.google.android.material.snackbar.Snackbar
import ru.mrrobot1413.recipeapp.R
import ru.mrrobot1413.recipeapp.misc.Utils.isInternetAvailable
import com.google.android.material.textfield.TextInputEditText
import ru.mrrobot1413.recipeapp.misc.Utils.hideKeyboard
import ru.mrrobot1413.recipeapp.ui.BottomNavigationManager
import ru.mrrobot1413.recipeapp.ui.CustomErrorItem
import ru.mrrobot1413.recipeapp.ui.CustomLoadingItem


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val adapter by lazy {
        HomeRecyclerViewAdapter { id, url ->
            (activity as BottomNavigationManager).openDetailedFragment(id, url, this)
        }
    }
    private val homeViewModel by lazy {
        ViewModelProvider(this).get(HomeViewModel::class.java)
    }
    private val noConnectionSnackbar by lazy {
        Snackbar.make(requireView(), getString(R.string.error_occurred), Snackbar.LENGTH_INDEFINITE)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
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

    private fun initViews(binding: FragmentHomeBinding) {
        (activity as BottomNavigationManager).showBottomNav()
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            initSearchView(searchEt = searchEt)
        }
    }

    private fun initSearchView(searchEt: TextInputEditText){
        searchEt.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                if (isInternetAvailable(requireContext())) {
                    noConnectionSnackbar.dismiss()
                    if(binding?.searchEt?.text.toString().trim().isNotEmpty()){
                        showRecycler()
                        attachRecyclerScroll()
                        homeViewModel.search(searchEt.text.toString(), 0)
                    } else {
                        Snackbar.make(requireView(), getString(R.string.empty_field), Snackbar.LENGTH_SHORT).show()
                    }
                } else {
                    noConnectionSnackbar.show()
                }
                hideKeyboard(requireActivity())
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
            homeViewModel.recipes.collectLatest {
                adapter.setRecipes(it)
            }
        }
    }

    private fun attachRecyclerScroll() {
        NoPaginate.with(binding?.recyclerView!!)
            .setOnLoadMoreListener {
                homeViewModel.offset += 25
                homeViewModel.search(binding?.searchEt?.text.toString(), homeViewModel.offset)
            }
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