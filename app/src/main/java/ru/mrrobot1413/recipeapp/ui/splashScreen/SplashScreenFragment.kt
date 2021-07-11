package ru.mrrobot1413.recipeapp.ui.splashScreen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.mrrobot1413.recipeapp.R
import ru.mrrobot1413.recipeapp.databinding.FragmentSplashScreenBinding

class SplashScreenFragment : Fragment() {

    private var binding: FragmentSplashScreenBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val animationSlideDown = AnimationUtils.loadAnimation(requireContext(), R.anim.slide_down)
        binding?.title?.startAnimation(animationSlideDown)
        lifecycleScope.launch {
            delay(2000)
            if(onBoardingFinished()){
                findNavController().navigate(R.id.action_splashScreenFragment_to_homeFragment)
            } else{
                findNavController().navigate(R.id.action_splashScreenFragment_to_viewPagerFragment)
            }
        }
    }

    private fun onBoardingFinished(): Boolean{
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}