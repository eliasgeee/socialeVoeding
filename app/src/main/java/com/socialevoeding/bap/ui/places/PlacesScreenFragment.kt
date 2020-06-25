package com.socialevoeding.bap.ui.places

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.socialevoeding.bap.R
import com.socialevoeding.bap.databinding.FragmentCategoryScreenBinding
import com.socialevoeding.domain.model.Category
import com.socialevoeding.domain.model.Place
import com.socialevoeding.framework.device.gps.GPSTracker
import com.socialevoeding.bap.ui.BaseFragment
import com.socialevoeding.presentation_android.viewModels.PlacesViewModel
import kotlinx.android.synthetic.main.toolbar.view.*
import kotlinx.android.synthetic.main.ttsbar.view.*
import org.koin.android.viewmodel.ext.android.viewModel

class PlacesScreenFragment() : BaseFragment() {

    private lateinit var binding: FragmentCategoryScreenBinding
    private var placesAdapter: PlacesAdapter? = null
    private val placesViewModel: PlacesViewModel by viewModel()
    private var category: Category? = null
    private var gpsTracker =
        com.socialevoeding.framework.device.gps.GPSTracker()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        gpsTracker.context = context
        gpsTracker.startGPSTracker()

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
        initTextToSpeech()

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            binding.tts.btn_tts_female.visibility = View.GONE
            binding.tts.btn_tts_male.visibility = View.GONE
        }

        placesAdapter = PlacesAdapter(requireContext(), object : PlacesClickListener {
            override fun onPlaceClick(place: Place) {
                placesViewModel.goToPlace(place)
            }
        })

        placesViewModel.setCurrentLocation(gpsTracker.getCurrentLocation())

        placesViewModel.currentPlaceLocation.observe(this, Observer {
            if (it != null)
                if (placesViewModel.places.value!!.isEmpty())
                placesViewModel.refreshPlaces()
        })

        binding.rvPlaces.layoutManager = LinearLayoutManager(requireContext())
        binding.rvPlaces.adapter = placesAdapter
    }

    private fun getTextToRead(): String {
        binding.tts.tts_btn_play.text = resources.getString(R.string.stop)
        val sb = StringBuilder()
        sb.append(binding.txtPlaces.text).append(" ")
        if (placesViewModel.places.value != null)
        for (place in placesViewModel.places.value!!) {
            sb.append(place.name).append(" ")
            if (place.isOpen)
                sb.append(resources.getString(R.string.isOpen)).append(" ")
            else
                sb.append(resources.getString(R.string.isGesloten)).append(" ")
            sb.append(resources.getString(R.string.distance)).append(" ")
            sb.append(place.distance / 1000).append(" kilometres ")
            sb.append(resources.getString(R.string.address)).append(" ")
            sb.append(place.address)
        }
        return sb.toString()
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

        placesViewModel.currentPlaceLocation.observe(this, Observer {
            binding.txtPlaces.text = resources.getString(R.string.eat_in_city, it.cityName)
        })

        binding.toolbar.btn_toolbar_back.setOnClickListener {
            goToStart()
        }

        binding.toolbar.btn_toolbar_home.setOnClickListener {
            goToStart()
        }

        binding.tts.tts_btn_play.setOnClickListener {
            if (binding.tts.tts_btn_play.text == resources.getString(R.string.read)) {
                speak(getTextToRead())
                binding.tts.tts_btn_play.text = resources.getString(R.string.stop)
            } else {
                stopSpeech()
                binding.tts.tts_btn_play.text = resources.getString(R.string.read)
            }
        }
    }

    private fun goToStart() {
        this.findNavController().navigate(
            PlacesScreenFragmentDirections.actionCategoryScreenFragmentToHomeScreenFragment()
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        gpsTracker.stopUsingGPS()
    }
}
