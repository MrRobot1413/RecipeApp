package ru.mrrobot1413.recipeapp.ui.onboarding.screens

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.mrrobot1413.recipeapp.R
import ru.mrrobot1413.recipeapp.databinding.FragmentOndoardingSecondBinding

class SecondOnboardingFragment : Fragment(){

    private var binding: FragmentOndoardingSecondBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOndoardingSecondBinding.inflate(inflater, container, false)

        binding?.btnNext?.setOnClickListener {
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
            onBoardingFinished()
        }

        return binding?.root
    }

    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}