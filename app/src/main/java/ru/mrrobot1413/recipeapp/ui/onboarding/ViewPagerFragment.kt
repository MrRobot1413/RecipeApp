package ru.mrrobot1413.recipeapp.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ru.mrrobot1413.recipeapp.databinding.FragmentViewPagerBinding
import ru.mrrobot1413.recipeapp.ui.onboarding.screens.FirstOnboardingFragment
import ru.mrrobot1413.recipeapp.ui.onboarding.screens.SecondOnboardingFragment

class ViewPagerFragment : Fragment() {
    private var binding: FragmentViewPagerBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val fragmentList = arrayListOf(
            FirstOnboardingFragment(),
            SecondOnboardingFragment()
        )

        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )

        binding?.viewPager?.adapter = adapter

        return binding?.root
    }
}