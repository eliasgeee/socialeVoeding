package com.socialevoeding.bap.ui.placedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.socialevoeding.bap.R
import com.socialevoeding.bap.databinding.FragmentLocationBinding
import com.socialevoeding.bap.domain.model.Category
import com.socialevoeding.bap.domain.model.Place
import com.socialevoeding.bap.ui.BaseFragment
import kotlinx.android.synthetic.main.toolbar.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class PlaceDetailsFragment : BaseFragment() {

    private var place: Place? = null
    private var category: Category? = null
    private lateinit var binding: FragmentLocationBinding
    private val locationViewModel: PlaceDetailsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      place = PlaceDetailsFragmentArgs.fromBundle(
            requireArguments()
        ).selectedPlace

        category = PlaceDetailsFragmentArgs.fromBundle(
            requireArguments()
        ).selectedCategory

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_location, container, false)

        binding.viewModel = locationViewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        if (place != null)
           locationViewModel.setCurrentLocation(place!!)

        setListeners()
    }

    private fun setListeners() {
        binding.toolbarLocation.btn_toolbar_back.setOnClickListener {
            this.findNavController().navigate(
                PlaceDetailsFragmentDirections.actionLocationFragmentToCategoryScreenFragment(category!!)
            )
        }

        binding.toolbarLocation.btn_toolbar_home.setOnClickListener {
            this.findNavController().navigate(
                PlaceDetailsFragmentDirections.actionLocationFragmentToHomeScreenFragment()
            )
        }
    }
}