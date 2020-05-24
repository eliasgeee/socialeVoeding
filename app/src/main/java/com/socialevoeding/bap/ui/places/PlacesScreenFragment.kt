package com.socialevoeding.bap.ui.places

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.socialevoeding.bap.R
import com.socialevoeding.bap.databinding.FragmentCategoryScreenBinding
import com.socialevoeding.bap.domain.model.Category
import com.socialevoeding.bap.domain.model.Place
import com.socialevoeding.bap.ui.BaseFragment
import kotlinx.android.synthetic.main.toolbar.view.*
import kotlinx.android.synthetic.main.ttsbar.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class PlacesScreenFragment : BaseFragment() {

    private lateinit var binding: FragmentCategoryScreenBinding
    private var placesAdapter: PlacesAdapter? = null
    private val placesViewModel: PlacesViewModel by viewModel()
    private var category: Category? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        category = PlacesScreenFragmentArgs.fromBundle(
            requireArguments()
        ).selectedCategory

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_category_screen, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        updateUi()
        startListeners()
    }

    private fun updateUi() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            binding.tts.btn_tts_female.visibility = View.GONE
            binding.tts.btn_tts_male.visibility = View.GONE
        }

        placesAdapter = PlacesAdapter(requireContext(), object : PlacesClickListener {
            override fun onPlaceClick(place: Place) {
                placesViewModel.goToPlace(place)
            }
        })

        binding.rvPlaces.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPlaces.adapter = placesAdapter
    }

    private fun startListeners() {
        placesViewModel.places.observe(this, Observer {
            placesAdapter!!.submitList(it)
        })

        placesViewModel.goToPlace.observe(this, Observer {
            if (it != null) {
                this.findNavController().navigate(
                    PlacesScreenFragmentDirections.actionCategoryScreenFragmentToLocationFragment(it, category!!)
                )

                placesViewModel.placeNavigated()
            }
        })

        placesViewModel.currentLocation.observe(this, Observer {
            binding.txtPlaces.text = resources.getString(R.string.eat_in_city, it.cityName)
        })

        binding.toolbar.btn_toolbar_back.setOnClickListener {
            goToStart()
        }

        binding.toolbar.btn_toolbar_home.setOnClickListener {
            goToStart()
        }

        binding.tts.btn_tts_male.setOnClickListener {
            initTextToSpeech(binding.tts.btn_tts_female, true)
        }
    }

    private fun goToStart() {
        this.findNavController().navigate(
            PlacesScreenFragmentDirections.actionCategoryScreenFragmentToHomeScreenFragment()
        )
    }
}
