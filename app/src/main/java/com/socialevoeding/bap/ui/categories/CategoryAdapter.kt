package com.socialevoeding.bap.ui.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.socialevoeding.bap.R
import com.socialevoeding.bap.databinding.RvCatItemBinding
import com.socialevoeding.bap.domain.model.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.rv_cat_item.view.*
import java.util.*

class CategoryAdapter(private val context : Context, private val clickListener: CategoryClickListener) :
    ListAdapter<Category, CategoryAdapter.CategoryViewHolder>(
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
            category: Category,
            clickListener: CategoryClickListener,
            context: Context
        ) {
            binding.clicklistener = clickListener
            binding.category = category
            binding.imgCategory.background = null

            if (category.name.toUpperCase(Locale.getDefault()) == "FOOD")
                Picasso.with(context).load(R.drawable.ic_category_food).into(binding.imgCategory)
            else
                Picasso.with(context).load(R.drawable.ic_category_food).into(binding.imgCategory)

            binding.txtCategory.text = category.name.toUpperCase(Locale.getDefault())

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

class CategoryDiffCallback : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldCategory: Category, newCategory: Category): Boolean {
        return newCategory.id == oldCategory.id
    }

    override fun areContentsTheSame(oldCategory: Category, newCategory: Category): Boolean {
        return newCategory.id == oldCategory.id
    }
}

interface CategoryClickListener {
    fun onCategoryClick(category: Category)
}