package com.socialevoeding.bap.ui.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.socialevoeding.bap.R
import com.socialevoeding.bap.databinding.FragmentHomeScreenBinding
import com.socialevoeding.bap.ui.BaseFragment
import com.socialevoeding.bap.util.hide
import com.socialevoeding.bap.util.show
import com.socialevoeding.presentation_android.ViewItem
import com.socialevoeding.presentation_android.ViewState
import com.socialevoeding.presentation_android.viewModels.CategoryViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CategoryScreenFragment : BaseFragment() {

    private var categoryAdapter: CategoryAdapter? = null
    private lateinit var binding: FragmentHomeScreenBinding
    private val categoryViewModel: CategoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home_screen, container, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        updateUi()
        setListeners()
    }

    private fun updateUi() {

        categoryAdapter = CategoryAdapter(requireContext(), object : CategoryClickListener {
            override fun onCategoryClick(category: ViewItem.CategoryViewItem) {
                categoryViewModel.goToCategory(category)
            }
        })

        binding.rvCatItems.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = categoryAdapter
        }
    }

    private fun setListeners() {
        categoryViewModel.viewState.observe(this, Observer {
            when (it) {
                is ViewState.Loading -> {
                    binding.errorMsg.hide()
                    binding.loadingSpinner.show()
                }
                is ViewState.Error -> {
                    binding.loadingSpinner.hide()
                    showError(it.errorMessage)
                }
                is ViewState.Succes<*> -> {
                    binding.errorMsg.hide()
                    binding.loadingSpinner.hide()
                    categoryAdapter!!.submitList(it.succes as List<ViewItem.CategoryViewItem>?)
                }
            }
        })

        categoryViewModel.goToCategory.observe(this, Observer {
            if (it != null) {
                this.findNavController().navigate(
                    CategoryScreenFragmentDirections.actionHomeScreenFragmentToCategoryScreenFragmen(it)
                    )
                categoryViewModel.onCategoryNavigated()
            }
        })
    }

    private fun showError(errorMsg: String) {
        binding.errorMsg.text = errorMsg
        binding.errorMsg.show()
    }
}
