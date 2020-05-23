package com.socialevoeding.bap.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.socialevoeding.bap.R
import com.socialevoeding.bap.ui.OnboadingViewModel
import org.koin.android.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass.
 */
class OnboardingFragment : Fragment() {

    private val onboadingViewModel : OnboadingViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onStart() {
        super.onStart()
        onboadingViewModel.getPlaces()
    }

}
