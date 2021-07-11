package ru.mrrobot1413.recipeapp.ui

import androidx.fragment.app.Fragment

interface BottomNavigationManager {

    fun showBottomNav()
    fun hideBottomNav()

    fun openDetailedFragment(id: Int, url: String, source: Fragment)
}