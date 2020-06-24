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
import com.socialevoeding.domain.model.Category
import com.socialevoeding.bap.ui.BaseFragment
import org.koin.android.viewmodel.ext.android.viewModel

const val REQUESTCODE_LOCATION = 1

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
            override fun onCategoryClick(category: Category) {
                categoryViewModel.goToCategory(category)
            }
        })

        binding.rvCatItems.apply {
            layoutManager = GridLayoutManager(activity, 2)
            adapter = categoryAdapter
        }
    }

    private fun setListeners() {
        categoryViewModel.categories.observe(this, Observer {
            categoryAdapter!!.submitList(it)
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
}
