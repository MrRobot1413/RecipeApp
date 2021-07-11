package ru.mrrobot1413.recipeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.mrrobot1413.recipeapp.R
import ru.mrrobot1413.recipeapp.databinding.ActivityMainBinding
import ru.mrrobot1413.recipeapp.ui.homeScreen.HomeFragment
import ru.mrrobot1413.recipeapp.ui.homeScreen.HomeFragmentDirections
import ru.mrrobot1413.recipeapp.ui.ingredientSearch.IngredientSearchFragmentDirections

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BottomNavigationManager {
    private var binding: ActivityMainBinding? = null
    private val navController by lazy {
        findNavController(R.id.fragmentContainerView)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        hideBottomNav()
        binding?.apply {
            bottomNavViewHolder.setNavigationChangeListener { _, position ->
                when(position){
                    0 -> {
                        val action = IngredientSearchFragmentDirections.actionIngredientSearchFragmentToHomeFragment()
                        navController.navigate(action)
                    }
                    1 -> {
                        val action = HomeFragmentDirections.actionHomeFragmentToIngredientSearchFragment()
                        navController.navigate(action)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun showBottomNav() {
        binding?.apply {
            bottomNavViewHolder.isVisible = true
        }
    }

    override fun hideBottomNav() {
        binding?.bottomNavViewHolder?.isVisible = false
    }

    override fun openDetailedFragment(id: Int, url: String, source: Fragment) {
        if(source is HomeFragment) {
            val action = HomeFragmentDirections.actionHomeFragmentToDetailedRecipeFragment().setId(id).setUrl(url)
            navController.navigate(action)
        } else {
            val action = IngredientSearchFragmentDirections.actionIngredientSearchFragmentToDetailedRecipeFragment().setId(id).setUrl(url)
            navController.navigate(action)
        }
    }
}