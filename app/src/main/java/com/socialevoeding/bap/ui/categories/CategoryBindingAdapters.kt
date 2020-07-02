package com.socialevoeding.bap.ui.categories

import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.socialevoeding.bap.R
import com.socialevoeding.presentation_android.ViewItem

@BindingAdapter("bg_cat")
fun LinearLayout.setBackground(category: ViewItem.CategoryViewItem?) {
    category.let {
        if (category?.id == 1) {
            setBackgroundColor(resources.getColor(R.color.pastel_yellow))
        }
        if (category?.id == 2) {
            setBackgroundColor(resources.getColor(R.color.pastel_blue))
        }
        if (category?.id == 3) {
            setBackgroundColor(resources.getColor(R.color.pastel_green))
        }
        if (category?.id == 4) {
            setBackgroundColor(resources.getColor(R.color.pastel_purple))
        }
        if (category?.id == 5) {
            setBackgroundColor(resources.getColor(R.color.pastel_red))
        }
        if (category?.id == 6) {
            setBackgroundColor(resources.getColor(R.color.pastel_darkblue))
        }
    }
}

@BindingAdapter("cat_name")
fun TextView.setCategoryName(category: ViewItem.CategoryViewItem?) {
    category.let {
        if (it!!.name == "FOOD")
            text = resources.getString(R.string.food)
    }
}