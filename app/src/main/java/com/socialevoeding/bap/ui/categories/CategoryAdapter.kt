package com.socialevoeding.bap.ui.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.socialevoeding.bap.R
import com.socialevoeding.bap.databinding.RvCatItemBinding
import com.socialevoeding.presentation_android.ViewItem
import java.util.*

class CategoryAdapter(private val context: Context, private val clickListener: CategoryClickListener) :
    ListAdapter<ViewItem.CategoryViewItem, CategoryAdapter.CategoryViewHolder>(
        CategoryDiffCallback()
    ) {

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.binding.catItem.setOnClickListener {
            clickListener.onCategoryClick(getItem(position)!!)
        }

        holder.fillViewItems(getItem(position)!!, clickListener, context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder.from(
            parent,
            viewType
        )
    }

    class CategoryViewHolder private constructor(val binding: RvCatItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun fillViewItems(
            category: ViewItem.CategoryViewItem,
            clickListener: CategoryClickListener,
            context: Context
        ) {
            binding.apply {
                binding.clicklistener = clickListener
                binding.category = category
                binding.imgCategory.background = null

                if (category.name.toUpperCase(Locale.getDefault()) == "FOOD") {
                    binding.imgCategory.load(R.drawable.ic_category_food)
                    binding.txtCategory.text = context.resources.getString(R.string.food).toUpperCase(
                        Locale.getDefault())
                } else {
                    binding.imgCategory.load(R.drawable.ic_category_food)
                    binding.txtCategory.text = "lorem ipsum".toUpperCase(Locale.getDefault())
                }
            }

            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup, viewType: Int): CategoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RvCatItemBinding.inflate(layoutInflater, parent, false)
                return CategoryViewHolder(
                    binding
                )
            }
        }
    }
}

class CategoryDiffCallback : DiffUtil.ItemCallback<ViewItem.CategoryViewItem>() {
    override fun areItemsTheSame(oldCategory: ViewItem.CategoryViewItem, newCategory: ViewItem.CategoryViewItem): Boolean {
        return newCategory.id == oldCategory.id
    }

    override fun areContentsTheSame(oldCategory: ViewItem.CategoryViewItem, newCategory: ViewItem.CategoryViewItem): Boolean {
        return newCategory.id == oldCategory.id
    }
}

interface CategoryClickListener {
    fun onCategoryClick(category: ViewItem.CategoryViewItem)
}