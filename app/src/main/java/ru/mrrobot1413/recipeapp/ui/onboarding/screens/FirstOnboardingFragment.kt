package ru.mrrobot1413.recipeapp.ui.onboarding.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import ru.mrrobot1413.recipeapp.R
import ru.mrrobot1413.recipeapp.databinding.FragmentOndoardingFirstBinding

class FirstOnboardingFragment : Fragment(){

    private var binding: FragmentOndoardingFirstBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOndoardingFirstBinding.inflate(inflater, container, false)

        binding?.btnNext?.setOnClickListener {
            val viewPager = activity?.findViewById<ViewPager2>(R.id.view_pager)
            viewPager?.currentItem = 1
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}